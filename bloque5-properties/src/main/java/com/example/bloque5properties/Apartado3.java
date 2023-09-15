package com.example.bloque5properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Apartado3 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String myURL = System.getenv("MYURL");

        if(myURL!=null){
            System.out.println("El valor de MYURL es: (" + myURL + ")");
        }else{
            System.out.println("La variable de entorno MYURL no está definida.");
        }
    }
}
