package com.campus.events.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event Entity — all getters/setters written explicitly (no Lombok).
 */
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Event title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Event date is required")
    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @NotNull(message = "Event time is required")
    @Column(name = "event_time")
    private LocalTime eventTime;

    @NotBlank(message = "Venue is required")
    @Size(max = 200)
    private String venue;

    @NotBlank(message = "Department is required")
    private String department;

    @NotNull(message = "Event type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    private EventType eventType;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 10000, message = "Capacity cannot exceed 10000")
    private Integer capacity;

    @Column(name = "registered_count")
    private Integer registeredCount = 0;

    @Enumerated(EnumType.STRING)
    private EventStatus status = EventStatus.UPCOMING;

    // ── Constructors ────────────────────────────────────────────────

    public Event() {}

    public Event(Long id, String title, String description, LocalDate eventDate,
                 LocalTime eventTime, String venue, String department,
                 EventType eventType, Integer capacity, Integer registeredCount,
                 EventStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.department = department;
        this.eventType = eventType;
        this.capacity = capacity;
        this.registeredCount = registeredCount;
        this.status = status;
    }

    // ── Getters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getEventDate() { return eventDate; }
    public LocalTime getEventTime() { return eventTime; }
    public String getVenue() { return venue; }
    public String getDepartment() { return department; }
    public EventType getEventType() { return eventType; }
    public Integer getCapacity() { return capacity; }
    public Integer getRegisteredCount() { return registeredCount; }
    public EventStatus getStatus() { return status; }

    // ── Setters ─────────────────────────────────────────────────────

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
    public void setEventTime(LocalTime eventTime) { this.eventTime = eventTime; }
    public void setVenue(String venue) { this.venue = venue; }
    public void setDepartment(String department) { this.department = department; }
    public void setEventType(EventType eventType) { this.eventType = eventType; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
    public void setRegisteredCount(Integer registeredCount) { this.registeredCount = registeredCount; }
    public void setStatus(EventStatus status) { this.status = status; }

    // ── Convenience methods ─────────────────────────────────────────

    public boolean hasAvailableSpots() {
        return registeredCount < capacity;
    }

    public int getAvailableSpots() {
        return capacity - registeredCount;
    }

    // ── Enums ───────────────────────────────────────────────────────

    public enum EventType {
        CONFERENCE, HACKATHON, WORKSHOP, CULTURAL,
        CAREER, EXHIBITION, SEMINAR, COMPETITION
    }

    public enum EventStatus {
        UPCOMING, ONGOING, COMPLETED, CANCELLED
    }
}
