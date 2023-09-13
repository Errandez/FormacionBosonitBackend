package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Block5CommandLineRunnerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Block5CommandLineRunnerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {}

    @PostConstruct
    void ejecuta(){
        System.out.println("Hola desde la clase inicial.");
    }
    @Bean
    CommandLineRunner ejecuta1(){
        return p -> {
            System.out.println("Hola desde la clase secundaria.");
        };
    }

    @Bean
    CommandLineRunner ejecuta2(){
        return p -> {
            System.out.println("Soy la tercera clase.");
        };
    }
}
