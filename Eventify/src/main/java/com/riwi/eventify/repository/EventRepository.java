package com.riwi.eventify.repository;

import com.riwi.eventify.models.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();

    public void save(Event event) {
        events.add(event);
    }

    public List<Event> findAll() {
        return events;
    }

    public Event findById(Long id) {
        //stream() Convierte la lista en flujo para poder filtrar.
        return events.stream()
                .filter(e -> e.getId().equals(id))
                //findFirst() Obtiene el primero encontrado.
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        return events.removeIf(e -> e.getId().equals(id));
    }



}
