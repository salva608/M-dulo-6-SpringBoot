package com.riwi.eventify.controller;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@Tag(name = "Eventos", description = "Operaciones relacionadas a los eventos")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Obtiene todos los eventos", description = "Retorna lista de eventos")
    @ApiResponse(responseCode = "200", description = "Eventos encontrados exitosamente")
    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @Operation(summary = "Registra un nuevo evento", description = "Crea un nuevo registro de eventos")
    @ApiResponse(responseCode = "201", description = "Evento creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error: el evento no pudo ser registrado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event saveEvent(@RequestBody Event event) {
        eventService.save(event);
        return event;
    }
}