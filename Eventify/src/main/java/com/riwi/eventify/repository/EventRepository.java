package com.riwi.eventify.repository;

import com.riwi.eventify.dto.EventSummaryDTO;
import com.riwi.eventify.models.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByNameContaining(String name);

    /**
     * Consulta optimizada que aplana los datos directamente al Record DTO.
     * Al usar FETCH/JOIN explícito con 'e.venue v', evitamos consultas N+1.
     * Hibernate aplicará automáticamente el filtro @SQLRestriction("active = true").
     */
    @Query("""
        SELECT new com.riwi.eventify.dto.EventSummaryDTO(
            e.id, 
            e.name, 
            e.date, 
            v.name, 
            v.city
        )
        FROM Event e
        JOIN e.venue v
        WHERE (:city IS NULL OR v.city = :city)
          AND (:dateStart IS NULL OR e.date >= :dateStart)
          AND (:dateEnd IS NULL OR e.date <= :dateEnd)
        ORDER BY e.date DESC
    """)
    Slice<EventSummaryDTO> findFilteredEvents(
            @Param("city") String city,
            @Param("dateStart") LocalDate dateStart,
            @Param("dateEnd") LocalDate dateEnd,
            Pageable pageable
    );
}