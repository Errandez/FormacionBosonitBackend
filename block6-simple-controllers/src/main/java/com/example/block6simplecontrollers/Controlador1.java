package com.example.block6simplecontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador1 {
    @GetMapping(value="/user/{nombre}")
    public String get(@PathVariable String nombre){
        return "Hola " + nombre;
    }

    @PostMapping(value = "/useradd", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona post(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Persona persona = om.readValue(json, Persona.class);
        persona.setEdad(persona.getEdad()+1);
        return persona;
    }
}
