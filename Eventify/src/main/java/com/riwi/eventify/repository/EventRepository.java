package com.riwi.eventify.repository;

import com.riwi.eventify.models.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();
    private Long currentId = 1L;

    public Event save(Event event) {
        event.setId(currentId++);
        events.add(event);
        return event;
    }

    public List<Event> findAll() {
        return events;
    }

    public Event findById(Long id) {
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        return events.removeIf(e -> e.getId().equals(id));
    }
}