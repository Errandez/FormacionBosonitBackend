package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.EntityNotFoundException;
import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.application.UnprocessableEntityException;
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


// APARTADO 2.

@RestController
@RequestMapping("/ejercicio2")
public class Controller2 {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping
    public ResponseEntity<PersonaOutputDto> addPersona(@RequestBody String json) throws JsonProcessingException,Exception {
        try {
            ObjectMapper om = new ObjectMapper();
            PersonaInputDto personaaux = om.readValue(json, PersonaInputDto.class);
            if (personaaux != null) {
                boolean error = false;
                String mensajeError = "";
                if (personaaux.getUsuario() != null && !personaaux.getUsuario().isEmpty()) {
                    if (personaaux.getUsuario().length() >= 6 && personaaux.getUsuario().length() <= 10) {

                    } else {
                        error = true;
                        mensajeError = "Usuario debe tener entre 6 y 10 caracteres.";
                    }

                } else {
                    error = true;
                    mensajeError += "Usuario no puede ser nulo. ";
                }
                if (personaaux.getName() != null && !personaaux.getName().isEmpty()) {

                } else {
                    error = true;
                    mensajeError += "Nombre no puede ser nulo. ";
                }
                if (personaaux.getPassword() != null && !personaaux.getPassword().isEmpty()) {

                } else {
                    error = true;
                    mensajeError += "Password no puede ser nulo. ";
                }

                if (personaaux.getCompany_email() != null && !personaaux.getCompany_email().isEmpty()) {

                } else {
                    error = true;
                    mensajeError = "Company_email no puede ser nulo. ";
                }
                if (personaaux.getPersonal_email() != null && !personaaux.getPersonal_email().isEmpty()) {

                } else {
                    error = true;
                    mensajeError += "Personal_email no puede ser nulo. ";
                }
                if (personaaux.getCity() != null && !personaaux.getCity().isEmpty()) {

                } else {
                    error = true;
                    mensajeError += "City no puede ser nulo. ";
                }
                if (personaaux.getActive() != null) {

                } else {
                    error = true;
                    mensajeError += "Active no puede ser nulo. ";
                }
                if (personaaux.getCreated_date() != null) {
                } else {
                    error = true;
                    mensajeError += "Created_date no puede ser nulo. ";
                }

                if (error) {
                    UnprocessableEntityException ex = new UnprocessableEntityException(mensajeError);
                    System.out.println("No se crea. ");
                    throw ex;
                } else {
                    URI location = URI.create("/persona");
                    System.out.println("Se crea. ");
                    return ResponseEntity.created(location).body(personaService.addPersona(personaaux));
                }
            } else {
                EntityNotFoundException ex = new EntityNotFoundException("No se encuentra registro");
                throw ex;
            }
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
                boolean error=false;
                String mensajeError="";
                if(personaaux.getUsuario()!=null  && !personaaux.getUsuario().isEmpty()){
                    if(personaaux.getUsuario().length()>=6 && personaaux.getUsuario().length()<=10){
                        persona.setUsuario(personaaux.getUsuario());
                    }else{
                        error=true;
                        mensajeError="Usuario debe tener entre 6 y 10 caracteres";
                    }

                }else{
                    error=true;
                    mensajeError="Usuario no puede ser nulo";
                }
                if(personaaux.getName()!=null  && !personaaux.getName().isEmpty()){
                    persona.setName(personaaux.getName());
                }else{
                    error=true;
                    mensajeError="Nombre no puede ser nulo";
                }
                if(personaaux.getPassword()!=null  && !personaaux.getPassword().isEmpty()){
                    persona.setPassword(personaaux.getPassword());
                }else{
                    error=true;
                    mensajeError="Password no puede ser nulo";
                }
                if(!personaaux.getSurname().isEmpty()){
                    persona.setSurname(personaaux.getSurname());
                }
                if(personaaux.getCompany_email()!=null  && !personaaux.getCompany_email().isEmpty()){
                    persona.setCompany_email(personaaux.getCompany_email());
                }else{
                    error=true;
                    mensajeError="Company_email no puede ser nulo";
                }
                if(personaaux.getPersonal_email()!=null  && !personaaux.getPersonal_email().isEmpty()){
                    persona.setPersonal_email(personaaux.getPersonal_email());
                }else{
                    error=true;
                    mensajeError="Personal_email no puede ser nulo";
                }
                if(personaaux.getCity()!=null  && !personaaux.getCity().isEmpty()){
                    persona.setCity(personaaux.getCity());
                }else{
                    error=true;
                    mensajeError="City no puede ser nulo";
                }
                if(personaaux.getActive()!=null){
                    persona.setActive(personaaux.getActive());
                }else{
                    error=true;
                    mensajeError="Active no puede ser nulo";
                }
                if(personaaux.getCreated_date()!=null ){
                    persona.setCreated_date(personaaux.getCreated_date());
                }else{
                    error=true;
                    mensajeError="Created_date no puede ser nulo";
                }

                persona.setImagen_url(personaaux.getImagen_url());

                persona.setTermination_date(personaaux.getTermination_date());

                if(error){
                    UnprocessableEntityException ex = new UnprocessableEntityException(mensajeError);
                    throw ex;
                }
            }else{
                EntityNotFoundException ex = new EntityNotFoundException("No se encuentra registro");
                throw ex;
            }
            personaService.updatePersona(persona);
            return  ResponseEntity.ok().body(personaService.addPersona(persona));
        } catch (UnprocessableEntityException e) {
            System.out.println("Date: " + e.getCustomError().getTimestamp());
            System.out.println("Http: " + e.getCustomError().getHttp());
            System.out.println("Mensaje: " + e.getCustomError().getMensaje());
            return ResponseEntity.notFound().build();
        }
        catch (EntityNotFoundException e) {
            System.out.println("Date: " + e.getCustomError().getTimestamp());
            System.out.println("Http: " + e.getCustomError().getHttp());
            System.out.println("Mensaje: " + e.getCustomError().getMensaje());
            return ResponseEntity.unprocessableEntity().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}