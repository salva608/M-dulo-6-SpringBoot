package com.riwi.eventify.controller;


import com.riwi.eventify.models.Venue;
import com.riwi.eventify.service.VenueService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/venue")
@AllArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @GetMapping
    public List<Venue> getAllVenue() {
        return venueService.findAll();
    }

    @PostMapping
    public void saveEvent(@RequestBody Venue venue) {
        venueService.save(venue);
    }
}