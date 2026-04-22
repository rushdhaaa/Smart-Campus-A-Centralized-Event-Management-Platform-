package com.campus.events.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Registration Entity — fully explicit, no Lombok.
 */
@Entity
@Table(
        name = "registrations",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"student_email", "event_id"},
                name = "uk_student_event"
        )
)
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Student name is required")
    @Size(min = 2, max = 100)
    @Column(name = "student_name", nullable = false)
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    @Column(name = "student_email", nullable = false)
    private String studentEmail;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Enter a valid 10-digit Indian mobile number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Event event;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status = RegistrationStatus.CONFIRMED;

    // ── Constructors ────────────────────────────────────────────────

    public Registration() {}

    public Registration(Long id, String studentName, String studentEmail,
                        String phoneNumber, Event event,
                        LocalDateTime registeredAt, RegistrationStatus status) {
        this.id = id;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.phoneNumber = phoneNumber;
        this.event = event;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    // ── Getters ─────────────────────────────────────────────────────

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getStudentEmail() { return studentEmail; }
    public String getPhoneNumber() { return phoneNumber; }
    public Event getEvent() { return event; }
    public LocalDateTime getRegisteredAt() { return registeredAt; }
    public RegistrationStatus getStatus() { return status; }

    // ── Setters ─────────────────────────────────────────────────────

    public void setId(Long id) { this.id = id; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEvent(Event event) { this.event = event; }
    public void setRegisteredAt(LocalDateTime registeredAt) { this.registeredAt = registeredAt; }
    public void setStatus(RegistrationStatus status) { this.status = status; }

    // ── Enum ────────────────────────────────────────────────────────

    public enum RegistrationStatus {
        CONFIRMED, CANCELLED, WAITLISTED
    }
}