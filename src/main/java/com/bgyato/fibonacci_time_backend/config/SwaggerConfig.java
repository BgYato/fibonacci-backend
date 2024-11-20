package com.bgyato.fibonacci_time_backend.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-api")
                .packagesToScan("com.bgyato.fibonacci_time_backend")
                .build();
    }

    @Bean
    public OpenApiCustomiser customOpenApi() {
        return openApi -> openApi.info(new Info().title("Fibonacci Time API")
                .description("API para generar y obtener series de Fibonacci con tiempos de ejecución")
                .version("v1"));
    }
}
