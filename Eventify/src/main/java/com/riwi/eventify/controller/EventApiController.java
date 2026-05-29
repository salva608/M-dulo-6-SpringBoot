package com.riwi.eventify.controller;

import com.riwi.eventify.dto.EventSummaryDTO;
import com.riwi.eventify.repository.EventRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Management de Eventos", description = "Endpoints profesionales para la consulta y administración de eventos en Eventify")
public class EventApiController {

    private final EventRepository eventRepository;

    @Operation(
            summary = "Consultar catálogo de eventos con filtros avanzados",
            description = "Recupera un fragmento (Slice) del catálogo de eventos ordenados cronológicamente de forma descendente. " +
                    "**Regla de Negocio Crítica:** Por motivos de auditoría y borrado lógico, este endpoint aplica una exclusión automática transparente; " +
                    "ningún evento marcado como inactivo (active = false) será retornado en los resultados."
    )
    @ApiResponse(responseCode = "200", description = "Catálogo recuperado exitosamente (Slice optimizado sin COUNT total)")
    @GetMapping
    public ResponseEntity<Slice<EventSummaryDTO>> getEvents(
            @Parameter(description = "Filtrar por la ciudad exacta donde se encuentra la sede", example = "Medellín")
            @RequestParam(required = false) String city,

            @Parameter(description = "Rango de fecha: Fecha inicial de búsqueda (Formato YYYY-MM-DD)", example = "2026-05-01")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,

            @Parameter(description = "Rango de fecha: Fecha final de búsqueda (Formato YYYY-MM-DD)", example = "2026-12-31")
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,

            @Parameter(description = "Número de página a recuperar (Basado en índice cero: 0 es la primera página)", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Cantidad máxima de registros permitidos por fragmento (Slice)", example = "10")
            @RequestParam(defaultValue = "10") int size
    ) {
        // Ejecutamos la consulta usando la interfaz Slice optimizada
        Slice<EventSummaryDTO> filteredEvents = eventRepository.findFilteredEvents(
                city, dateStart, dateEnd, PageRequest.of(page, size)
        );

        return ResponseEntity.ok(filteredEvents);
    }
}