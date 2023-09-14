package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Tercera implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Soy la tercera clase.");
        System.out.println("Cantidad de valores pasados como parámetro al ejecutar el programa: " + args.length);
        for(int i=0;i<args.length;i++){
            System.out.println(i+1 + ". " +args[i]);
        }
    }
}
