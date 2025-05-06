package com.mahesh.webflux;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Reactive In-Memory API",
                version = "1.0.0",
                description = "A lightweight reactive service built with Spring Boot WebFlux"
        )
)
@SpringBootApplication
@EnableAdminServer
public class SpringbootWebfluxServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebfluxServiceApplication.class, args);
    }
}
