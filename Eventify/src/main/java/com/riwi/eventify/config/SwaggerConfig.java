package com.riwi.eventify.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Gestión de Coders - Riwi",
                version = "5.0",
                description = "Documentación interactiva para la administración de programadores y clanes.",
                contact = @Contact(
                        name = "salva",
                        email = "salva@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
