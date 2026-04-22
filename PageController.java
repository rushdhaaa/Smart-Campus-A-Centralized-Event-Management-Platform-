package com.campus.events.controller;

import com.campus.events.dto.StudentDTO;
import com.campus.events.model.Event;
import com.campus.events.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {

    private final EventService eventService;

    public PageController(EventService eventService) {
        this.eventService = eventService;
    }

    // ── Page 1: Landing Page ─────────────────────────────────────────

    @GetMapping("/")
    public String landing() {
        return "landing";
    }

    // ── Page 2: Student Login Page ───────────────────────────────────

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("student") StudentDTO student,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        redirectAttributes.addFlashAttribute("studentName", student.getName());
        redirectAttributes.addFlashAttribute("studentEmail", student.getEmail());
        redirectAttributes.addFlashAttribute("studentPhone", student.getPhone());

        return "redirect:/dashboard";
    }

    // ── Page 3: Student Dashboard ────────────────────────────────────

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        if (!model.containsAttribute("studentName"))  model.addAttribute("studentName", "");
        if (!model.containsAttribute("studentEmail")) model.addAttribute("studentEmail", "");
        if (!model.containsAttribute("studentPhone")) model.addAttribute("studentPhone", "");

        model.addAttribute("events", eventService.getUpcomingEvents());
        model.addAttribute("departments", eventService.getAllDepartments());
        model.addAttribute("eventTypes", Event.EventType.values());
        return "dashboard";
    }

    // ── Admin Login Page ─────────────────────────────────────────────

    @GetMapping("/admin/login")
    public String adminLogin(Model model,
                             @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid username or password.");
        }
        return "admin-login";
    }

    // ── Admin redirect from /admin → /admin/dashboard ────────────────

    @GetMapping("/admin")
    public String adminRoot() {
        return "redirect:/admin/dashboard";
    }

    // ── Admin Dashboard ──────────────────────────────────────────────

    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        model.addAttribute("stats", eventService.getStatistics());
        model.addAttribute("departments", eventService.getAllDepartments());
        model.addAttribute("eventTypes", Event.EventType.values());
        model.addAttribute("statuses", Event.EventStatus.values());
        model.addAttribute("newEvent", new Event());
        return "admin";
    }
}