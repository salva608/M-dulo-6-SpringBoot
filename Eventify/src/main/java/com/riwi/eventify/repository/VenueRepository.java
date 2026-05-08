package com.riwi.eventify.repository;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.models.Venue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class VenueRepository {
    private List<Venue> venues = new ArrayList<>();

    public void save(Venue venue) {
        venues.add(venue);
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
        return venues.removeIf(e -> e.getId().equals(id));
    }

}
