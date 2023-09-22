package com.example.block6personcontrollers;

import com.oracle.svm.core.annotate.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controlador2 {

    @Autowired
    private List<Ciudad> ciudades;

    // Apartado 1.

    @GetMapping(value="/controlador2/getPersona", produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona get1(@RequestParam("nombre") String nombre,
                        @RequestParam("poblacion") String poblacion,
                        @RequestParam("edad") int edad){
        Persona p = new Persona(nombre, poblacion, edad*2);
        return p;
    }

    // Apartado 2.

    @GetMapping(value="/controlador2/getCiudades", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ciudad> get2(){
        return ciudades;
    }
}
