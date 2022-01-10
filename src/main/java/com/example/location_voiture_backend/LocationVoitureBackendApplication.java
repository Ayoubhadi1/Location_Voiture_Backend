package com.example.location_voiture_backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.RequestHandler;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class LocationVoitureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationVoitureBackendApplication.class, args);
    }



}
