package com.campus.events.controller;

import com.campus.events.dto.FeedbackDTO;
import com.campus.events.dto.StudentDTO;
import com.campus.events.model.Feedback;
import com.campus.events.model.Registration;
import com.campus.events.service.FeedbackService;
import com.campus.events.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RegistrationController — REST API for registrations and feedback.
 * No Lombok — uses explicit constructor injection.
 */
@RestController
public class RegistrationController {

    private final RegistrationService registrationService;
    private final FeedbackService feedbackService;

    // Constructor injection — replaces @RequiredArgsConstructor
    public RegistrationController(RegistrationService registrationService,
                                  FeedbackService feedbackService) {
        this.registrationService = registrationService;
        this.feedbackService = feedbackService;
    }

    // ── REGISTRATION ENDPOINTS ───────────────────────────────────────

    @PostMapping("/api/events/{eventId}/register")
    public ResponseEntity<Map<String, Object>> register(
            @PathVariable Long eventId,
            @Valid @RequestBody StudentDTO studentDTO) {

        Registration reg = registrationService.registerForEvent(eventId, studentDTO);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Registration successful!");
        response.put("registrationId", reg.getId());
        response.put("eventTitle", reg.getEvent().getTitle());
        response.put("studentName", reg.getStudentName());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/registrations")
    public ResponseEntity<List<Registration>> getMyRegistrations(
            @RequestParam String email) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEmail(email));
    }

    @DeleteMapping("/api/registrations/{id}")
    public ResponseEntity<Map<String, String>> cancelRegistration(
            @PathVariable Long id,
            @RequestParam String email) {
        registrationService.cancelRegistration(id, email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Registration cancelled successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/admin/events/{eventId}/registrations")
    public ResponseEntity<List<Registration>> getEventRegistrations(
            @PathVariable Long eventId) {
        return ResponseEntity.ok(registrationService.getRegistrationsByEvent(eventId));
    }

    // ── FEEDBACK ENDPOINTS ───────────────────────────────────────────

    @PostMapping("/api/feedback")
    public ResponseEntity<Map<String, String>> submitFeedback(
            @Valid @RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.submitFeedback(feedbackDTO);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Feedback submitted successfully! Thank you.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/events/{eventId}/feedback")
    public ResponseEntity<Map<String, Object>> getEventFeedback(
            @PathVariable Long eventId) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByEvent(eventId);
        Double avgRating = feedbackService.getAverageRating(eventId);

        Map<String, Object> response = new HashMap<>();
        response.put("feedbacks", feedbacks);
        response.put("averageRating", avgRating);
        response.put("count", feedbacks.size());
        return ResponseEntity.ok(response);
    }
}
