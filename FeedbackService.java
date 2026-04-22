package com.campus.events.service;

import com.campus.events.dto.FeedbackDTO;
import com.campus.events.model.Event;
import com.campus.events.model.Feedback;
import com.campus.events.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

/**
 * FeedbackService — no Lombok, explicit constructor injection and logger.
 */
@Service
@Transactional
public class FeedbackService {

    private static final Logger log = Logger.getLogger(FeedbackService.class.getName());

    private final FeedbackRepository feedbackRepository;
    private final EventService eventService;

    public FeedbackService(FeedbackRepository feedbackRepository,
                           EventService eventService) {
        this.feedbackRepository = feedbackRepository;
        this.eventService = eventService;
    }

    public Feedback submitFeedback(FeedbackDTO dto) {
        log.info("Submitting feedback for event ID " + dto.getEventId());

        Event event = eventService.getEventById(dto.getEventId());

        Feedback feedback = new Feedback();
        feedback.setStudentName(dto.getStudentName());
        feedback.setStudentEmail(dto.getStudentEmail());
        feedback.setRating(dto.getRating());
        feedback.setComments(dto.getComments());
        feedback.setEvent(event);
        feedback.setSubmittedAt(LocalDateTime.now());

        return feedbackRepository.save(feedback);
    }

    @Transactional(readOnly = true)
    public List<Feedback> getFeedbackByEvent(Long eventId) {
        return feedbackRepository.findByEventId(eventId);
    }

    @Transactional(readOnly = true)
    public Double getAverageRating(Long eventId) {
        return feedbackRepository.getAverageRatingByEventId(eventId);
    }
}
