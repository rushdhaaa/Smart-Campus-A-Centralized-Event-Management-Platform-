package com.campus.events.service;

import com.campus.events.model.Event;
import com.campus.events.model.Registration;
import com.campus.events.repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * CalendarService
 * ───────────────
 * Generates RFC 5545-compliant .ics file content so students can import
 * an event into Google Calendar, Apple Calendar, Outlook, etc.
 *
 * Also detects time conflicts with already-registered events for the same email.
 */
@Service
public class CalendarService {

    private static final Logger log = Logger.getLogger(CalendarService.class.getName());

    // ICS datetime format — yyyyMMdd'T'HHmmss
    private static final DateTimeFormatter ICS_FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss");
    // ICS date-only format
    private static final DateTimeFormatter ICS_DATE_FMT =
            DateTimeFormatter.ofPattern("yyyyMMdd");

    private final RegistrationRepository registrationRepository;

    public CalendarService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    // ─────────────────────────────────────────────────────────────────
    // CONFLICT DETECTION
    // ─────────────────────────────────────────────────────────────────

    /**
     * Returns true when the target event overlaps (same date ± 2 h window)
     * with any event the student has already registered for.
     *
     * Rule: events on the same date whose start times are within 2 hours
     * of each other are considered conflicting.
     */
    public boolean hasTimeConflict(String studentEmail, Event targetEvent) {
        if (studentEmail == null || studentEmail.isBlank()) return false;

        List<Registration> existing =
                registrationRepository.findByStudentEmail(studentEmail);

        LocalDate  targetDate = targetEvent.getEventDate();
        LocalTime  targetTime = targetEvent.getEventTime();

        for (Registration reg : existing) {
            Event registered = reg.getEvent();
            if (registered == null) continue;
            if (!targetDate.equals(registered.getEventDate())) continue;
            if (registered.getEventTime() == null || targetTime == null) continue;

            long minutesDiff = Math.abs(
                targetTime.toSecondOfDay() - registered.getEventTime().toSecondOfDay()
            ) / 60L;

            if (minutesDiff < 120) {   // within 2 hours
                log.info("Conflict detected: " + targetEvent.getTitle()
                         + " conflicts with " + registered.getTitle()
                         + " for " + studentEmail);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the title of the first conflicting event, or null if none.
     * Useful for the warning message.
     */
    public String getConflictingEventTitle(String studentEmail, Event targetEvent) {
        if (studentEmail == null || studentEmail.isBlank()) return null;

        List<Registration> existing =
                registrationRepository.findByStudentEmail(studentEmail);

        LocalDate targetDate = targetEvent.getEventDate();
        LocalTime targetTime = targetEvent.getEventTime();

        for (Registration reg : existing) {
            Event registered = reg.getEvent();
            if (registered == null) continue;
            if (!targetDate.equals(registered.getEventDate())) continue;
            if (registered.getEventTime() == null || targetTime == null) continue;

            long minutesDiff = Math.abs(
                targetTime.toSecondOfDay() - registered.getEventTime().toSecondOfDay()
            ) / 60L;

            if (minutesDiff < 120) return registered.getTitle();
        }
        return null;
    }

    // ─────────────────────────────────────────────────────────────────
    // ICS GENERATION
    // ─────────────────────────────────────────────────────────────────

    /**
     * Builds a complete RFC 5545 VCALENDAR string for one event.
     * The student can save this as  event-name.ics  and open it with
     * Google Calendar / Outlook / Apple Calendar.
     */
    public String generateIcsContent(Event event, String studentName, String studentEmail) {
        LocalDate date      = event.getEventDate();
        LocalTime startTime = event.getEventTime() != null
                              ? event.getEventTime() : LocalTime.of(9, 0);
        LocalTime endTime   = startTime.plusHours(2);   // assume 2-hour duration

        String dtStart = LocalDateTime.of(date, startTime).format(ICS_FMT);
        String dtEnd   = LocalDateTime.of(date, endTime  ).format(ICS_FMT);
        String now     = LocalDateTime.now().format(ICS_FMT);
        String uid     = UUID.randomUUID() + "@smartcampus";

        String summary     = icsEscape(event.getTitle());
        String description = icsEscape(
                event.getDescription() != null ? event.getDescription() : "");
        String location    = icsEscape(
                event.getVenue() + ", " + event.getDepartment());
        String organizer   = "Smart Campus Event Management";
        String attendee    = studentEmail != null ? studentEmail : "";

        return "BEGIN:VCALENDAR\r\n" +
               "VERSION:2.0\r\n" +
               "PRODID:-//Smart Campus//Event Management//EN\r\n" +
               "CALSCALE:GREGORIAN\r\n" +
               "METHOD:PUBLISH\r\n" +
               "BEGIN:VEVENT\r\n" +
               "UID:" + uid + "\r\n" +
               "DTSTAMP:" + now + "\r\n" +
               "DTSTART:" + dtStart + "\r\n" +
               "DTEND:" + dtEnd + "\r\n" +
               "SUMMARY:" + summary + "\r\n" +
               "DESCRIPTION:" + description + "\r\n" +
               "LOCATION:" + location + "\r\n" +
               "ORGANIZER;CN=" + organizer + ":mailto:noreply@smartcampus.edu\r\n" +
               (attendee.isBlank() ? "" :
                   "ATTENDEE;CN=" + icsEscape(studentName != null ? studentName : attendee)
                   + ":mailto:" + attendee + "\r\n") +
               "STATUS:CONFIRMED\r\n" +
               "TRANSP:OPAQUE\r\n" +
               "END:VEVENT\r\n" +
               "END:VCALENDAR\r\n";
    }

    // Escapes special characters per RFC 5545
    private String icsEscape(String value) {
        if (value == null) return "";
        return value.replace("\\", "\\\\")
                    .replace(";", "\\;")
                    .replace(",", "\\,")
                    .replace("\n", "\\n")
                    .replace("\r", "");
    }
}
