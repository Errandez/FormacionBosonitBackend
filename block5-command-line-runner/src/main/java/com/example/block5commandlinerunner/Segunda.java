package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Segunda implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hola desde la clase secundaria.");
    }
}
