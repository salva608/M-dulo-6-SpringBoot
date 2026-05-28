package com.riwi.eventify.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestión de Eventify - Riwi")
                        .version("1.0")
                        .description("Documentación interactiva para la administración de eventos y lugares (Venues).")
                        .contact(new Contact()
                                .name("salva")
                                .email("salva@gmail.com")
                        )
                );
    }

    @Bean
    public GroupedOpenApi apiRoutes() {
        // TASK 3: Filtrar para que Swagger SOLO documente los endpoints de la API (/api/)
        return GroupedOpenApi.builder()
                .group("Eventify-API")
                .pathsToMatch("/api/**")
                .build();
    }
}