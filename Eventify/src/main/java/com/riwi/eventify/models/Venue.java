package com.riwi.eventify.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa un (lugar) en el ecosistema de Eventify")
public class Venue {
    @Schema(description = "Id unico generado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Schema(description = "Nombre completo del lugar", example = "Riwi")
    private String name;
    @Schema(description = "Direccion del lugar", example = "Carrera 63a #74-72")
    private String address;
    @Schema(description = "Capacidad de personas", example = "100")
    private int capacity;
}
