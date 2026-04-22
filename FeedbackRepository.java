package com.campus.events.repository;

import com.campus.events.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByEventId(Long eventId);

    List<Feedback> findByStudentEmail(String email);

    @Query("SELECT COALESCE(AVG(f.rating), 0) FROM Feedback f WHERE f.event.id = :eventId")
    Double getAverageRatingByEventId(Long eventId);
}
