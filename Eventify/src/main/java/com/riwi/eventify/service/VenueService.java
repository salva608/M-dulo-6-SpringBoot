package com.riwi.eventify.service;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.models.Venue;
import com.riwi.eventify.repository.VenueRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    //crear un nuevo lugar
    public Venue save (Venue venue) {
        validateVenue(venue);
        return venueRepository.save(venue);
    }

    //obtener lugares
    public List<Venue> findAll() {
        return venueRepository.findAll();
    }

    //obtener lugar por id
    public Venue findById(Long id) {
        return venueRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lUGAR NO ENCONTRADO"));
    }

    //Actualizar lugar
    public Venue update(Long id, Venue updateVenue) {
        Venue existingVenue = findById(id);
        validateVenue(updateVenue);

        existingVenue.setName(updateVenue.getName());
        existingVenue.setAddress(updateVenue.getAddress());
        existingVenue.setCapacity(updateVenue.getCapacity());

        return venueRepository.save(existingVenue);
    }

    //eliminar lugar
    public void delete(Long id) {
        Venue existingVenue = findById(id);
        venueRepository.delete(existingVenue);
    }


    private void validateVenue(Venue venue) {
        if (venue.getName() == null || venue.getName().isBlank()) {
            throw new IllegalArgumentException("El nombre del lugar no puede estar vacío");
        }
        if (venue.getAddress() == null || venue.getAddress().isBlank()) {
            throw new IllegalArgumentException("La dirección no puede quedar vacía");
        }
        if (venue.getCapacity() <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
    }

}