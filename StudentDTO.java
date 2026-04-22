package com.campus.events.dto;

import jakarta.validation.constraints.*;

/**
 * StudentDTO — Data Transfer Object for the Login form.
 * Fully explicit, no Lombok.
 */
public class StudentDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^[6-9]\\d{9}$",
        message = "Enter a valid 10-digit Indian mobile number"
    )
    private String phone;

    // ── Constructors ────────────────────────────────────────────────

    public StudentDTO() {}

    public StudentDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // ── Getters ─────────────────────────────────────────────────────

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    // ── Setters ─────────────────────────────────────────────────────

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
}
