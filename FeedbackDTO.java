package com.campus.events.dto;

import jakarta.validation.constraints.*;

/**
 * FeedbackDTO — fully explicit, no Lombok.
 */
public class FeedbackDTO {

    @NotBlank(message = "Name is required")
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String studentEmail;

    @NotNull(message = "Rating is required")
    @Min(1) @Max(5)
    private Integer rating;

    @Size(max = 500)
    private String comments;

    @NotNull(message = "Event ID is required")
    private Long eventId;

    // ── Constructors ────────────────────────────────────────────────

    public FeedbackDTO() {}

    public FeedbackDTO(String studentName, String studentEmail,
                       Integer rating, String comments, Long eventId) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.rating = rating;
        this.comments = comments;
        this.eventId = eventId;
    }

    // ── Getters ─────────────────────────────────────────────────────

    public String getStudentName() { return studentName; }
    public String getStudentEmail() { return studentEmail; }
    public Integer getRating() { return rating; }
    public String getComments() { return comments; }
    public Long getEventId() { return eventId; }

    // ── Setters ─────────────────────────────────────────────────────

    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public void setRating(Integer rating) { this.rating = rating; }
    public void setComments(String comments) { this.comments = comments; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
}
