package com.example.block6personcontrollers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Configuration
public class Persona {
    private String nombre;
    private String poblacion;
    private int edad;

}
