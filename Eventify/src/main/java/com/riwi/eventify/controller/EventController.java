package com.riwi.eventify.controller;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @PostMapping
    public void saveEvent(@RequestBody Event event) {
        eventService.save(event);
    }
}
