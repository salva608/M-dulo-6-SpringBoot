package com.riwi.eventify.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@Schema(description = "Representa una categoría temática de eventos")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id único generado", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 100)
    @Schema(description = "Nombre de la categoría", example = "Concerts")
    private String name;

    @Column(length = 255)
    @Schema(description = "Descripción detallada de la categoría", example = "Eventos musicales en vivo y festivales")
    private String description;
}