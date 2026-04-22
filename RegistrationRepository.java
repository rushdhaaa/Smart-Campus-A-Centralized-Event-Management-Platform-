package com.campus.events.repository;

import com.campus.events.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<Registration> findByEventId(Long eventId);

    List<Registration> findByStudentEmail(String email);

    Optional<Registration> findByStudentEmailAndEventId(String email, Long eventId);

    boolean existsByStudentEmailAndEventId(String email, Long eventId);

    Long countByEventId(Long eventId);
}
