package com.riwi.eventify.service;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event save(Event event) {
        if (event.getName() == null || event.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento no puede estar vacio");
        }
        if (event.getDate() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }
}