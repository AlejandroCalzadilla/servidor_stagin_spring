package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

     /**
     * The main entry point for the Spring Boot application.
     * @param args Command line arguments.
     */

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

     /**
     * A simple health check endpoint.
     * @return A string indicating the application is up.
     */
    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
