package com.campus.events.service;

import com.campus.events.exception.EventNotFoundException;
import com.campus.events.model.Event;
import com.campus.events.model.Event.EventStatus;
import com.campus.events.model.Event.EventType;
import com.campus.events.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
@Transactional
public class EventService {

    private static final Logger log = Logger.getLogger(EventService.class.getName());

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // ── READ Operations ──────────────────────────────────────────────

    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        log.info("Fetching all events");
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Event> getUpcomingEvents() {
        // Return ALL events for dashboard — admin sees everything
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Event> searchEvents(String department, String eventType,
                                    String status, LocalDate fromDate, LocalDate toDate) {
        EventType type = (eventType != null && !eventType.isBlank())
                ? EventType.valueOf(eventType) : null;
        EventStatus st = (status != null && !status.isBlank())
                ? EventStatus.valueOf(status) : null;
        String dept = (department != null && department.isBlank()) ? null : department;
        return eventRepository.searchEvents(dept, type, st, fromDate, toDate);
    }

    // ── WRITE Operations ─────────────────────────────────────────────

    public Event createEvent(Event event) {
        log.info("Creating new event: " + event.getTitle());
        // Only default registeredCount — respect status chosen by admin
        event.setRegisteredCount(0);
        if (event.getStatus() == null) {
            event.setStatus(EventStatus.UPCOMING);
        }
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        log.info("Updating event with ID: " + id);
        Event existing = getEventById(id);

        existing.setTitle(updatedEvent.getTitle());
        existing.setDescription(updatedEvent.getDescription());
        existing.setEventDate(updatedEvent.getEventDate());
        existing.setEventTime(updatedEvent.getEventTime());
        existing.setVenue(updatedEvent.getVenue());
        existing.setDepartment(updatedEvent.getDepartment());
        existing.setEventType(updatedEvent.getEventType());
        existing.setCapacity(updatedEvent.getCapacity());
        if (updatedEvent.getStatus() != null) {
            existing.setStatus(updatedEvent.getStatus());
        }

        return eventRepository.save(existing);
    }

    public void deleteEvent(Long id) {
        log.info("Deleting event with ID: " + id);
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException(id);
        }
        eventRepository.deleteById(id);
    }

    public void incrementRegistrationCount(Long eventId) {
        Event event = getEventById(eventId);
        event.setRegisteredCount(event.getRegisteredCount() + 1);
        eventRepository.save(event);
    }

    public void decrementRegistrationCount(Long eventId) {
        Event event = getEventById(eventId);
        if (event.getRegisteredCount() > 0) {
            event.setRegisteredCount(event.getRegisteredCount() - 1);
            eventRepository.save(event);
        }
    }

    // ── Admin Statistics ─────────────────────────────────────────────

    @Transactional(readOnly = true)
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalEvents", eventRepository.count());
        stats.put("upcomingEvents", eventRepository.countByStatus(EventStatus.UPCOMING));
        stats.put("completedEvents", eventRepository.countByStatus(EventStatus.COMPLETED));
        stats.put("cancelledEvents", eventRepository.countByStatus(EventStatus.CANCELLED));
        stats.put("totalRegistrations", eventRepository.getTotalRegistrations());
        stats.put("departments", eventRepository.findAllDepartments());
        return stats;
    }

    @Transactional(readOnly = true)
    public List<String> getAllDepartments() {
        return eventRepository.findAllDepartments();
    }
}