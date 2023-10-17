package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.EntityNotFoundException;
import com.example.block7crudvalidation.application.ServiceImpl.PersonaServiceImpl;
import com.example.block7crudvalidation.application.UnprocessableEntityException;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


// APARTADO 2.

@RestController
@RequestMapping("/ejercicio2")
public class Controller2 {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody PersonaInputDto personaInputDto) throws JsonProcessingException,Exception {
        try {
            ObjectMapper om = new ObjectMapper();


            URI location = URI.create("/persona");
            System.out.println("Se crea. ");
            return ResponseEntity.created(location).body(personaService.addPersona(personaInputDto));

        }catch (UnprocessableEntityException e) {
            System.out.println("Date: " + e.getCustomError().getTimestamp());
            System.out.println("Http: " + e.getCustomError().getHttp());
            System.out.println("Mensaje: " + e.getCustomError().getMensaje());
            return ResponseEntity.notFound().build();
        }

    }



    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        }catch(Exception e){
            EntityNotFoundException ex = new EntityNotFoundException("No se encuentra registro");
            ex.lanzar();
            System.out.println("Date: " + ex.getCustomError().getTimestamp());
            System.out.println("Http: " + ex.getCustomError().getHttp());
            System.out.println("Mensaje: " + ex.getCustomError().getMensaje());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonaByName(@PathVariable String nombre){
        try{
            return ResponseEntity.ok().body(personaService.getPersonaByName(nombre));
        }catch(Exception e){
            EntityNotFoundException ex = new EntityNotFoundException("No se encuentra registro");
            ex.lanzar();
            System.out.println("Date: " + ex.getCustomError().getTimestamp());
            System.out.println("Http: " + ex.getCustomError().getHttp());
            System.out.println("Mensaje: " + ex.getCustomError().getMensaje());
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id) {
        try {
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("persona with id: " + id + " was deleted");
        } catch (Exception e) {
            EntityNotFoundException ex = new EntityNotFoundException("No se encuentra registro");
            ex.lanzar();
            System.out.println("Date: " + ex.getCustomError().getTimestamp());
            System.out.println("Http: " + ex.getCustomError().getHttp());
            System.out.println("Mensaje: " + ex.getCustomError().getMensaje());
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping
    public Iterable<PersonaOutputDto> getAllPersonas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return personaService.getAllPersonas(pageNumber, pageSize);
    }

    @PutMapping(value="/{id}")
    public PersonaOutputDto updatePersona(@PathVariable int id,@RequestBody PersonaInputDto personaInputDto) throws Exception {
        personaInputDto.setId_persona(id);
        return personaService.updatePersona(personaInputDto);
    }
}