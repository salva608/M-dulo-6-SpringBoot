package com.riwi.eventify.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
@SQLRestriction("active = true") // Requerimiento: Filtro global automático para ignorar los eliminados lógicamente
@Schema(description = "Representa un (Evento) en el ecosistema de Eventify")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id unico generado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Schema(description = "Nombre completo del evento", example = "Concierto de Arcangel")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Fecha del evento", example = "2026-05-15") // Formato estándar ISO para el ejemplo de Swagger
    private LocalDate date;

    @Column(nullable = false, length = 500)
    @Schema(description = "Descripcion del evento", example = "Increible concierto de Arcangel con invitados especiales")
    private String description;

    // --- NUEVOS ATRIBUTOS DE LA HU ---

    @Column(nullable = false)
    @Schema(description = "Estado del evento (Soft Delete)", example = "true")
    private boolean active = true; // Atributo para el borrado lógico

    // Relación Many-to-One obligatoria con Venue
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venue_id", nullable = false)
    @Schema(description = "Lugar o sede asignada obligatoriamente al evento")
    private Venue venue;

    // Relación Many-to-Many con Category (Con guardado en cascada)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "events_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @Schema(description = "Categorías temáticas asociadas al evento")
    private Set<Category> categories = new HashSet<>();

    public Event(Long id, String name, LocalDate date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
        this.active = true;
    }

    // Requerimiento: Método para ejecutar el borrado lógico de forma semántica
    public void softDelete() {
        this.active = false;
    }
}