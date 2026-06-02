/*package com.riwi.eventify.config;

import com.riwi.eventify.models.Event;
import com.riwi.eventify.models.Venue;
import com.riwi.eventify.repository.EventRepository;
import com.riwi.eventify.repository.VenueRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class ApplicationConfig {

    @Bean
    public CommandLineRunner loadData(EventRepository eventRepo, VenueRepository venueRepo) {
        return args -> {
            // Pasamos 'null' en el ID para que Hibernate use el autoincremento (IDENTITY) de forma correcta
            Venue place = new Venue(null, "Auditorio Riwi", "Medellin","Calle 123", 100);
            Event party = new Event(null, "Spring Boot Workshop", LocalDate.now(), "Aprenderás DI");

            // Guardado seguro en la base de datos en memoria
            venueRepo.save(place);
            eventRepo.save(party);

            System.out.println(">>> Datos iniciales cargados con éxito.");
        };
    }
}
*/