package com.riwi.eventify.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa un (Evento) en el ecosistema de Eventify")
public class Event {
    @Schema(description = "Id unico generado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre completo del evento", example = "Concierto de Arcangel")
    private String name;
    @Schema(description = "Fecha del evento", example = "15/05/2026")
    private LocalDate date;
    @Schema(description = "Descripcion del evento", example = "Increible concierto de Arcangel con invitados especiales")
    private String description;

}
