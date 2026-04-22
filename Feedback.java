package com.campus.events.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Feedback Entity — fully explicit, no Lombok.
 */
@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(name = "student_name")
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email
    @Column(name = "student_email")
    private String studentEmail;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating cannot exceed 5")
    private Integer rating;

    @Size(max = 500, message = "Comments cannot exceed 500 characters")
    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt = LocalDateTime.now();

    // ── Constructors ────────────────────────────────────────────────

    public Feedback() {}

    public Feedback(Long id, String studentName, String studentEmail,
                    Integer rating, String comments, Event event,
                    LocalDateTime submittedAt) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.rating = rating;
        this.comments = comments;
        this.event = event;
        this.submittedAt = submittedAt;
    }

    // ── Getters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getStudentEmail() { return studentEmail; }
    public Integer getRating() { return rating; }
    public String getComments() { return comments; }
    public Event getEvent() { return event; }
    public LocalDateTime getSubmittedAt() { return submittedAt; }

    // ── Setters ─────────────────────────────────────────────────────

    public void setId(Long id) { this.id = id; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }
    public void setEvent(Event event) { this.event = event; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
}
