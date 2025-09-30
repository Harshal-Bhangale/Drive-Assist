package com.app.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI driveAssistOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Drive Assist API").version("v1").description("Smart Road Assistance APIs"))
                .externalDocs(new ExternalDocumentation().description("Docs").url("https://example.com/docs"));
    }
}



