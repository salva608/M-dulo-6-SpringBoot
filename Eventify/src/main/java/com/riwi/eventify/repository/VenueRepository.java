package com.riwi.eventify.repository;

import com.riwi.eventify.models.Venue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VenueRepository {
    private List<Venue> venues = new ArrayList<>();
    private Long currentId = 1L;

    public Venue save(Venue venue) {
        venue.setId(currentId++);
        venues.add(venue);
        return venue;
    }

    public List<Venue> findAll() {
        return venues;
    }

    public Venue findById(Long id) {
        return venues.stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Long id) {
        return venues.removeIf(v -> v.getId().equals(id));
    }
}