package com.riwi.eventify.controller;

import com.riwi.eventify.models.Venue;
import com.riwi.eventify.service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
@AllArgsConstructor
@Tag(name = "Lugares", description = "Operaciones relacionadas a los lugares")
public class VenueController {

    private final VenueService venueService;

    @Operation(summary = "Obtiene todos los lugares", description = "Retorna lista de venues")
    @ApiResponse(responseCode = "200", description = "Lugares encontrados exitosamente")
    @GetMapping
    public List<Venue> getAllVenue() {
        return venueService.findAll();
    }

    @Operation(summary = "Registra un nuevo lugar", description = "Crea un nuevo registro de venue")
    @ApiResponse(responseCode = "201", description = "Lugar creado exitosamente")
    @ApiResponse(responseCode = "400", description = "Error: el lugar no pudo ser registrado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Venue saveVenue(@RequestBody Venue venue) {
        venueService.save(venue);
        return venue;
    }
}