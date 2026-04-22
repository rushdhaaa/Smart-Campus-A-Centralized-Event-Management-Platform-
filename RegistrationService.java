package com.campus.events.service;

import com.campus.events.dto.StudentDTO;
import com.campus.events.exception.RegistrationException;
import com.campus.events.model.Event;
import com.campus.events.model.Registration;
import com.campus.events.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * RegistrationService — no Lombok, explicit constructor injection and logger.
 */
@Service
@Transactional
public class RegistrationService {

    private static final Logger log = Logger.getLogger(RegistrationService.class.getName());

    private final RegistrationRepository registrationRepository;
    private final EventService eventService;

    // Constructor injection — replaces @RequiredArgsConstructor
    public RegistrationService(RegistrationRepository registrationRepository,
                               EventService eventService) {
        this.registrationRepository = registrationRepository;
        this.eventService = eventService;
    }

    public Registration registerForEvent(Long eventId, StudentDTO studentDTO) {
        log.info("Registering " + studentDTO.getEmail() + " for event ID " + eventId);

        // 1. Get the event
        Event event = eventService.getEventById(eventId);

        // 2. Check event status
        if (event.getStatus() != Event.EventStatus.UPCOMING) {
            throw new RegistrationException(
                "Cannot register for this event. Status: " + event.getStatus()
            );
        }

        // 3. Check duplicate registration
        if (registrationRepository.existsByStudentEmailAndEventId(
                studentDTO.getEmail(), eventId)) {
            throw new RegistrationException("You are already registered for this event!");
        }

        // 4. Check capacity
        if (!event.hasAvailableSpots()) {
            throw new RegistrationException("Sorry, this event is full. No seats available.");
        }

        // 5. Create and save registration
        Registration registration = new Registration();
        registration.setStudentName(studentDTO.getName());
        registration.setStudentEmail(studentDTO.getEmail());
        registration.setPhoneNumber(studentDTO.getPhone());
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());
        registration.setStatus(Registration.RegistrationStatus.CONFIRMED);

        Registration saved = registrationRepository.save(registration);

        // 6. Increment event count
        eventService.incrementRegistrationCount(eventId);

        log.info("Registration successful. ID: " + saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Registration> getRegistrationsByEmail(String email) {
        return registrationRepository.findByStudentEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Registration> getRegistrationsByEvent(Long eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    public void cancelRegistration(Long registrationId, String studentEmail) {
        Registration reg = registrationRepository.findById(registrationId)
            .orElseThrow(() -> new RegistrationException("Registration not found"));

        if (!reg.getStudentEmail().equalsIgnoreCase(studentEmail)) {
            throw new RegistrationException("You can only cancel your own registrations");
        }

        reg.setStatus(Registration.RegistrationStatus.CANCELLED);
        registrationRepository.save(reg);
        eventService.decrementRegistrationCount(reg.getEvent().getId());
    }
}
