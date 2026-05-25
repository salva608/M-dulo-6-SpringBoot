package com.riwi.eventify.service;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    // Crear un nuevo evento
    public Event save(Event event) {
        validateEvent(event);
        return eventRepository.save(event);
    }

    // Obtener todos lps eventos
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    // Obtener un evento por ID
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Evento no encontrado con ID: " + id));
    }

    // Actualizar un evento
    public Event update(Long id, Event updatedEvent) {
        Event existingEvent = findById(id);
        validateEvent(updatedEvent);

        // Actualizar campos
        existingEvent.setName(updatedEvent.getName());
        existingEvent.setDate(updatedEvent.getDate());
        existingEvent.setDescription(updatedEvent.getDescription());

        return eventRepository.save(existingEvent);
    }

    // Eliminar un evento
    public void delete(Long id) {
        Event existingEvent = findById(id); // valida que exista o lanza 404
        eventRepository.delete(existingEvent);
    }

    private void validateEvent(Event event) {
        if (event.getName() == null || event.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del evento no puede estar vacío");
        }
        if (event.getDate() == null) {
            throw new IllegalArgumentException("La fecha es obligatoria");
        }
    }
}