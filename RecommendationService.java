package com.campus.events.service;

import com.campus.events.dto.RecommendationResult;
import com.campus.events.dto.RecommendationResult.MatchLevel;
import com.campus.events.model.Event;
import com.campus.events.repository.EventRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

/**
 * RecommendationService
 * ─────────────────────
 * Calls the Google Gemini API to score every event against
 * the student's interest string, then returns a ranked list.
 *
 * Fallback: if the API call fails for any reason the service returns an
 * empty list so the UI can show the "Recommendations not available" message.
 */
@Service
public class RecommendationService {

    private static final Logger log = Logger.getLogger(RecommendationService.class.getName());

    // ── FIX 1: Read Gemini key/model from application.properties ────────
    @Value("${gemini.api.key:}")
    private String geminiApiKey;

    @Value("${gemini.api.model:gemini-2.5-flash}")
    private String geminiModel;

    // ── FIX 2: Correct Gemini endpoint (model name is part of URL) ───────
    private static final String GEMINI_URL_TEMPLATE =
            "https://generativelanguage.googleapis.com/v1beta/models/%s:generateContent?key=%s";

    private final EventRepository eventRepository;
    private final RestTemplate    restTemplate;
    private final ObjectMapper    objectMapper;

    public RecommendationService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.restTemplate    = new RestTemplate();
        this.objectMapper    = new ObjectMapper();
    }

    // ─────────────────────────────────────────────────────────────────
    // PUBLIC API
    // ─────────────────────────────────────────────────────────────────

    /**
     * Returns events ranked by relevance to the student's interest string.
     * Only events with a score > 0 are returned, sorted descending.
     */
    public List<RecommendationResult> getRecommendedEvents(String interest) {
        if (interest == null || interest.isBlank()) {
            return Collections.emptyList();
        }

        List<Event> allEvents = eventRepository.findAll();
        if (allEvents.isEmpty()) return Collections.emptyList();

        Map<Long, RecommendationResult> results = new LinkedHashMap<>();

        try {
            results = callGeminiForScores(interest.trim(), allEvents);
        } catch (Exception ex) {
            log.warning("Gemini call failed — returning empty recommendations. Reason: " + ex.getMessage());
            return Collections.emptyList();
        }

        // Sort by matchScore DESC, keep only score > 0
        List<RecommendationResult> sorted = new ArrayList<>(results.values());
        sorted.sort(Comparator.comparingInt(RecommendationResult::getMatchScore).reversed());
        sorted.removeIf(r -> r.getMatchScore() == 0);
        return sorted;
    }

    // ─────────────────────────────────────────────────────────────────
    // PRIVATE — Gemini integration
    // ─────────────────────────────────────────────────────────────────

    /**
     * Builds a single prompt containing all events and asks Gemini to
     * return a JSON array of {id, score, reason} objects.
     * This costs only ONE API call regardless of event count.
     */
    private Map<Long, RecommendationResult> callGeminiForScores(
            String interest, List<Event> events) throws Exception {

        // ── Build the events catalogue for the prompt ────────────────
        StringBuilder catalogue = new StringBuilder();
        for (Event e : events) {
            catalogue.append("ID:").append(e.getId())
                     .append(" | TITLE:").append(e.getTitle())
                     .append(" | TYPE:").append(e.getEventType())
                     .append(" | DESC:").append(
                             e.getDescription() != null
                             ? e.getDescription().substring(0, Math.min(120, e.getDescription().length()))
                             : "")
                     .append("\n");
        }

        // ── FIX 3: Gemini uses a single "text" prompt, not system/user roles ──
        String fullPrompt =
            "You are an event recommendation engine for a university campus portal. " +
            "You must respond ONLY with a valid JSON array. No markdown, no explanation, no code fences.\n\n" +
            "Student interests: \"" + interest + "\"\n\n" +
            "Events:\n" + catalogue +
            "\nFor each event return a JSON object with exactly these fields:\n" +
            "  id      (integer — the event ID)\n" +
            "  score   (integer 0-100 — relevance to the student's interests)\n" +
            "  reason  (string — one concise sentence explaining the match)\n\n" +
            "Return a JSON array of all events sorted by score descending.\n" +
            "Example: [{\"id\":1,\"score\":90,\"reason\":\"Directly covers AI topics\"},{\"id\":2,\"score\":10,\"reason\":\"Unrelated to interests\"}]";

        // ── FIX 4: Build Gemini request body format ──────────────────
        // Gemini expects: { "contents": [{ "parts": [{ "text": "..." }] }] }
        Map<String, Object> textPart = new LinkedHashMap<>();
        textPart.put("text", fullPrompt);

        Map<String, Object> content = new LinkedHashMap<>();
        content.put("parts", List.of(textPart));

        Map<String, Object> requestBody = new LinkedHashMap<>();
        requestBody.put("contents", List.of(content));

        // Optional: tune generation for deterministic JSON output
        Map<String, Object> genConfig = new LinkedHashMap<>();
        genConfig.put("temperature", 0.2);
        genConfig.put("maxOutputTokens", 8192);
        requestBody.put("generationConfig", genConfig);

        // ── FIX 5: Gemini uses API key as query param, not Bearer token ─
        String url = String.format(GEMINI_URL_TEMPLATE, geminiModel, geminiApiKey);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = objectMapper.writeValueAsString(requestBody);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        log.info("Calling Gemini API for recommendations, interest: " + interest);

        ResponseEntity<String> response =
            restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
            throw new RuntimeException("Gemini returned non-200: " + response.getStatusCode()
                    + " body: " + response.getBody());
        }

        // ── FIX 6: Parse Gemini response format ──────────────────────
        // Gemini returns: { "candidates": [{ "content": { "parts": [{ "text": "..." }] } }] }
        JsonNode root    = objectMapper.readTree(response.getBody());
        String   rawText = root.path("candidates")
                               .get(0)
                               .path("content")
                               .path("parts")
                               .get(0)
                               .path("text")
                               .asText();

        log.info("Gemini raw response: " + rawText);

        // Strip possible markdown code fences (just in case)
        String content2 = rawText
                .replaceAll("(?s)```json\\s*", "")
                .replaceAll("(?s)```\\s*", "")
                .trim();

        // ── Parse the scores array ───────────────────────────────────
        JsonNode scoresArray = objectMapper.readTree(content2);

        // Build event id → Event map for quick lookup
        Map<Long, Event> eventMap = new HashMap<>();
        for (Event e : events) eventMap.put(e.getId(), e);

        Map<Long, RecommendationResult> results = new LinkedHashMap<>();
        for (JsonNode node : scoresArray) {
            long   id     = node.path("id").asLong();
            int    score  = Math.max(0, Math.min(100, node.path("score").asInt()));
            String reason = node.path("reason").asText("No reason provided");

            Event event = eventMap.get(id);
            if (event == null) continue;   // safety — skip unknown ids

            MatchLevel level = toMatchLevel(score);
            results.put(id, new RecommendationResult(event, level, score, reason));
        }
        return results;
    }

    private MatchLevel toMatchLevel(int score) {
        if (score >= 65) return MatchLevel.HIGH;
        if (score >= 35) return MatchLevel.MEDIUM;
        return MatchLevel.LOW;
    }
}