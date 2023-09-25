package com.example.block7crud.controller;

import com.example.block7crud.application.PersonaServiceImpl;
import com.example.block7crud.controller.DTO.PersonaInputDto;
import com.example.block7crud.controller.DTO.PersonaOutputDto;
import com.example.block7crud.domain.Persona;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/persona")
public class Controller {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        PersonaInputDto persona = om.readValue(json,PersonaInputDto.class);
        URI location = URI.create("/persona");
        return ResponseEntity.created(location).body(personaService.addPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaOutputDto> getPersonaById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(personaService.getPersonaById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<PersonaOutputDto>> getPersonaByName(@PathVariable String nombre){
        try{
            return ResponseEntity.ok().body(personaService.getPersonaByName(nombre));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deletePersonaById(@PathVariable int id) {
        try {
            personaService.deletePersonaById(id);
            return ResponseEntity.ok().body("persona with id: " + id + " was deleted");
        } catch (Exception e) {
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
    public ResponseEntity<PersonaOutputDto> updatePersona(@PathVariable int id,@RequestBody String json) {
        try {
            ResponseEntity<PersonaOutputDto> p = this.getPersonaById(id);
            PersonaOutputDto per = p.getBody();
            PersonaInputDto persona = new PersonaInputDto(per.getId(),per.getName(), per.getPoblacion(),per.getEdad());
            ObjectMapper om = new ObjectMapper();
            PersonaInputDto personaaux = om.readValue(json,PersonaInputDto.class);
            if(personaaux!= null){
                if(personaaux.getName()!=null  && !personaaux.getName().isEmpty()){
                    persona.setName(personaaux.getName());
                }
                if(personaaux.getPoblacion()!=null  && !personaaux.getPoblacion().isEmpty()){
                    persona.setPoblacion(personaaux.getPoblacion());
                }
            }
            personaService.updatePersona(persona);
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}