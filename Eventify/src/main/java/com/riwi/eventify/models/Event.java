package com.riwi.eventify.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events")
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
    @Schema(description = "Fecha del evento", example = "15/05/2026")
    private LocalDate date;
    @Column(nullable = false, length = 500)
    @Schema(description = "Descripcion del evento", example = "Increible concierto de Arcangel con invitados especiales")
    private String description;


}
