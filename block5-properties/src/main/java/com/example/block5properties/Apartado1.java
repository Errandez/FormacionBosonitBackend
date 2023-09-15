package com.example.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class Apartado1 {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private Integer number;

    //Cuando añadimos el new.property al SO se mostrará la del SO.

    @Value("${new.property:new.property no tiene valor}")
    private String property;



    @Bean
    public void Apartado1(){
        System.out.println("El valor de greeting es: ("+greeting+")");
        System.out.println("El valor de my.number es: ("+number+")");
        System.out.println("El valor de new.property es: ("+property+")");
    }


}
