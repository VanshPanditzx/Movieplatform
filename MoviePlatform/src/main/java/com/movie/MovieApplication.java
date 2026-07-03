package com.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
        System.out.println("🎬 Movie Platform Started!");
        System.out.println("🌐 Open → http://localhost:8080");
    }
}