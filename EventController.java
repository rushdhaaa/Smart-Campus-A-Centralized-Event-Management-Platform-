package com.campus.events.controller;

import com.campus.events.model.Event;
import com.campus.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * EventController — REST API for events.
 * No Lombok — uses explicit constructor injection.
 */
@RestController
public class EventController {

    private final EventService eventService;

    // Constructor injection — replaces @RequiredArgsConstructor
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // ── PUBLIC ENDPOINTS ─────────────────────────────────────────────

    @GetMapping("/api/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

    @GetMapping("/api/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    @GetMapping("/api/events/search")
    public ResponseEntity<List<Event>> searchEvents(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String eventType,
            @RequestParam(required = false) String status,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {

        return ResponseEntity.ok(
            eventService.searchEvents(department, eventType, status, fromDate, toDate)
        );
    }

    // ── ADMIN ENDPOINTS ──────────────────────────────────────────────

    @PostMapping("/api/admin/events")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {
        Event created = eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/api/admin/events/{id}")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @DeleteMapping("/api/admin/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/admin/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        return ResponseEntity.ok(eventService.getStatistics());
    }

    @GetMapping("/api/admin/events")
    public ResponseEntity<List<Event>> getAllEventsAdmin() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }
}
