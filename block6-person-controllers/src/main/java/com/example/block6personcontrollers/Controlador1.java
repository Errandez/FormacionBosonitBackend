package com.example.block6personcontrollers;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@Configuration
public class Controlador1 {

    private List<Ciudad> ciudades = new ArrayList<>();

    // Apartado 1.

    @GetMapping(value="/controlador1/addPersona", produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona get1(@RequestHeader("nombre") String nombre,
                        @RequestHeader("poblacion") String poblacion,
                        @RequestHeader("edad") int edad){
        Persona p = new Persona(nombre, poblacion, edad);
        RestTemplate rt = new RestTemplate();
        p=rt.getForObject("http://localhost:8080/controlador2/getPersona?nombre={nombre}" +
                "&poblacion={poblacion}&edad={edad}", Persona.class, p.getNombre(), p.getPoblacion(),p.getEdad());

        return p;
    }

    // Apartado 2.

    @PostMapping(value = "/controlador1/addCiudad", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public void post1(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Ciudad ciudad= om.readValue(json, Ciudad.class);
        ciudades.add(ciudad);
        generaLista();
    }

    @Bean
    public List<Ciudad> generaLista(){
        return ciudades;
    }



}
