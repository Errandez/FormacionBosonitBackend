package com.example.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Apartado3_2 implements CommandLineRunner {

    @Value("${myURL2:NO_tengo_valor}")
    private String myURL2;
    @Override
    public void run(String... args) throws Exception {
        String myURL = System.getenv("MYURL2");

        if(myURL!=null){
            myURL2 = myURL;
        }

        System.out.println("El valor de MYURL2 es: (" + myURL2 + ")");
    }
}
