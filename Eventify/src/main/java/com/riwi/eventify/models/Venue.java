package com.riwi.eventify.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "venues")
@Schema(description = "Representa un (lugar) en el ecosistema de Eventify")
public class Venue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id unico generado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    @Column(nullable = false, length = 255)
    @Schema(description = "Nombre completo del lugar", example = "Riwi")
    private String name;
    @Column(nullable = false, length = 100)
    @Schema(description = "Direccion del lugar", example = "Carrera 63a #74-72")
    private String address;
    @Column(nullable = false)
    @Schema(description = "Capacidad de personas", example = "100")
    private Integer capacity;
}
