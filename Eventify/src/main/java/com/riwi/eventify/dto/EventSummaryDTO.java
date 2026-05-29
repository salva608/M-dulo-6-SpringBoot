package com.riwi.eventify.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Estructura optimizada y aplanada para listados masivos de eventos")
public record EventSummaryDTO(
        @Schema(description = "Id del evento", example = "1")
        Long id,

        @Schema(description = "Nombre del evento", example = "Concierto de Arcangel")
        String eventName,

        @Schema(description = "Fecha del evento", example = "2026-05-15")
        LocalDate date,

        @Schema(description = "Nombre del lugar o sede", example = "Riwi")
        String venueName,

        @Schema(description = "Ciudad donde se celebra", example = "Medellín")
        String city
) {}