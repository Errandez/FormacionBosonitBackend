package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaOutputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


// APARTADO 1.

@RestController
@RequestMapping("/persona")
public class Controller {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody String json) throws JsonProcessingException,Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            PersonaInputDto persona = om.readValue(json, PersonaInputDto.class);
            boolean error=false;
            if(persona.getUsuario()==null || persona.getUsuario().isEmpty()){
                System.out.println("Usuario no puede ser nulo");
                error = true;
            }

            if(persona.getUsuario().length()<6 || persona.getUsuario().length()>10){
                System.out.println("Usuario debe tener entre 6 y 10 caracteres");
                error = true;
            }

            if(persona.getPassword()==null || persona.getPassword().isEmpty()){
                System.out.println("Password no puede ser nulo");
                error = true;
            }

            if(persona.getName()==null|| persona.getName().isEmpty()){
                System.out.println("Name no puede ser nulo");
                error = true;
            }

            if(persona.getCompany_email()==null || persona.getCompany_email().isEmpty()){
                System.out.println("Company_email no puede ser nulo");
                error = true;
            }

            if(persona.getPersonal_email()==null || persona.getPersonal_email().isEmpty()){
                System.out.println("Personal_email no puede ser nulo");
                error = true;
            }

            if(persona.getCity()==null || persona.getCity().isEmpty()){
                System.out.println("City no puede ser nulo");
                error = true;
            }

            if(persona.getActive()==null ){
                System.out.println("Active no puede ser nulo");
                error = true;
            }

            if(persona.getCreated_date()==null){
                System.out.println("Created_date no puede ser nulo");
                error = true;
            }

            if(!error) {
                URI location = URI.create("/persona");
                return ResponseEntity.created(location).body(personaService.addPersona(persona));
            }else{
                return ResponseEntity.unprocessableEntity().build();
            }
        }catch (Exception ex){
            return ResponseEntity.unprocessableEntity().build();
        }
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
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = formato.format(per.getCreated_date());
            String d2 = formato.format(per.getTermination_date());
            PersonaInputDto persona = new PersonaInputDto(per.getId(),per.getUsuario(), per.getPassword(),per.getName(),per.getSurname(),
                per.getCompany_email(),per.getPersonal_email(),per.getCity(),per.getActive(),d1,per.getImagen_url(),
                d2, per.getId_student());
            ObjectMapper om = new ObjectMapper();
            PersonaInputDto personaaux = om.readValue(json,PersonaInputDto.class);
            if(personaaux!= null){
                if(personaaux.getUsuario()!=null  && !personaaux.getUsuario().isEmpty()){
                    persona.setUsuario(personaaux.getUsuario());
                }
                if(personaaux.getName()!=null  && !personaaux.getName().isEmpty()){
                    persona.setName(personaaux.getName());
                }
                if(personaaux.getPassword()!=null  && !personaaux.getPassword().isEmpty()){
                    persona.setPassword(personaaux.getPassword());
                }
                if(!personaaux.getSurname().isEmpty()){
                    persona.setSurname(personaaux.getSurname());
                }
                if(personaaux.getCompany_email()!=null  && !personaaux.getCompany_email().isEmpty()){
                    persona.setCompany_email(personaaux.getCompany_email());
                }
                if(personaaux.getPersonal_email()!=null  && !personaaux.getPersonal_email().isEmpty()){
                    persona.setPersonal_email(personaaux.getPersonal_email());
                }
                if(personaaux.getCity()!=null  && !personaaux.getCity().isEmpty()){
                    persona.setCity(personaaux.getCity());
                }
                if(personaaux.getActive()!=null){
                    persona.setActive(personaaux.getActive());
                }
                if(personaaux.getCreated_date()!=null ){
                    persona.setCreated_date(personaaux.getCreated_date());
                }

                persona.setImagen_url(personaaux.getImagen_url());

                persona.setTermination_date(personaaux.getTermination_date());


            }
            personaService.updatePersona(persona);
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}