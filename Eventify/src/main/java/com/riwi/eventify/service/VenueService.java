package com.riwi.eventify.service;

import com.riwi.eventify.models.Venue;
import com.riwi.eventify.repository.VenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public Venue save(Venue venue) {
        if (venue.getName() == null || venue.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del lugar no puede estar vacio");
        }
        if (venue.getAddress() == null || venue.getAddress().isBlank()) {
            throw new IllegalArgumentException("La direccion no puede quedar vacia");
        }
        if (venue.getCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor de 0");
        }
        return venueRepository.save(venue);
    }

    public List<Venue> findAll() {
        return venueRepository.findAll();
    }
}