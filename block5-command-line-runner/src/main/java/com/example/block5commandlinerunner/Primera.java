package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Primera {
    @PostConstruct
    void ejecuta(){
        System.out.println("Hola desde la clase inicial.");
    }
}
