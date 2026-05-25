package com.riwi.eventify.config;


import com.riwi.eventify.models.Event;
import com.riwi.eventify.models.Venue;
import com.riwi.eventify.repository.EventRepository;
import com.riwi.eventify.repository.VenueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Configuration
public class ApplicationConfig {

    @Bean
    public CommandLineRunner loadData(EventRepository eventRepo, VenueRepository venueRepo) {
        return args -> {
            // Aquí creas tus objetos iniciales
            Venue place = new Venue(1L, "Auditorio Riwi", "Calle 123", 100);
            Event party = new Event(1L, "Spring Boot Workshop", LocalDate.now(), "Aprenderás DI");

            // Los guardas en tus repositorios (que ya deben tener el método save)
            venueRepo.save(place);
            eventRepo.save(party);

            System.out.println(">>> Datos iniciales cargados con éxito.");
        };
    }
}
