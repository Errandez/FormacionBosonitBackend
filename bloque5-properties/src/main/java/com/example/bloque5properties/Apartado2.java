package com.example.bloque5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component

public class Apartado2 {

    @Value("${greeting1}")
    private String greeting;

    @Value("${my.number1}")
    private Integer number;

    @Value("${new.property1:new.property1 no tiene valor}")
    private String property;

    @Bean
    public void Ejecuta2(){
        System.out.println("El valor de greeting es: ("+greeting+")");
        System.out.println("El valor de my.number es: ("+number+")");
        System.out.println("El valor de new.property es: ("+property+")");
    }
}
