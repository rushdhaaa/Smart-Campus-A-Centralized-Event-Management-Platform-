package com.campus.events.repository;

import com.campus.events.model.Event;
import com.campus.events.model.Event.EventStatus;
import com.campus.events.model.Event.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByStatus(EventStatus status);

    List<Event> findByDepartmentIgnoreCase(String department);

    List<Event> findByEventType(EventType eventType);

    List<Event> findByEventDateGreaterThanEqual(LocalDate date);

    List<Event> findByStatusOrderByEventDateAsc(EventStatus status);

    List<Event> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT e FROM Event e WHERE " +
           "(:department IS NULL OR LOWER(e.department) = LOWER(:department)) AND " +
           "(:eventType IS NULL OR e.eventType = :eventType) AND " +
           "(:status IS NULL OR e.status = :status) AND " +
           "(:fromDate IS NULL OR e.eventDate >= :fromDate) AND " +
           "(:toDate IS NULL OR e.eventDate <= :toDate) " +
           "ORDER BY e.eventDate ASC")
    List<Event> searchEvents(
        @Param("department") String department,
        @Param("eventType") EventType eventType,
        @Param("status") EventStatus status,
        @Param("fromDate") LocalDate fromDate,
        @Param("toDate") LocalDate toDate
    );

    @Query("SELECT COALESCE(SUM(e.registeredCount), 0) FROM Event e")
    Long getTotalRegistrations();

    Long countByStatus(EventStatus status);

    @Query("SELECT DISTINCT e.department FROM Event e ORDER BY e.department")
    List<String> findAllDepartments();
}
