package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.EntityNotFoundException;
import com.example.block7crudvalidation.application.ServiceImpl.PersonaServiceImpl;
import com.example.block7crudvalidation.application.UnprocessableEntityException;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;


// APARTADO 2.

@RestController
@RequestMapping("/person")
public class Controller2 {
    @Autowired
    PersonaServiceImpl personaService;

    @PostMapping("/addperson")
    public ResponseEntity<PersonaOutputDto> addPersona(@Valid @RequestBody PersonaInputDto personaInputDto) throws JsonProcessingException,Exception {
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

    @GetMapping("/mayorQue")
    public Iterable<PersonaOutputDto> mayorQue(@RequestParam(required = false) String usuario,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String surname,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date created_date,
                                               @RequestParam(required = false) String CondicionFecha,
                                               @RequestParam(defaultValue = "usuario",required = false) String orden,
                                               @RequestParam(defaultValue = "asc", required = false) String direccion,
                                               @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                               @RequestParam(defaultValue = "4", required = false) Integer pageSize){
        HashMap<String, Object> data = new HashMap<>();
        if(usuario!=null && !usuario.isEmpty())data.put("usuario",usuario);
        if(name!=null && !name.isEmpty())data.put("name",name);
        if(surname!=null && !surname.isEmpty())data.put("surname",surname);
        if(created_date!=null)data.put("created_date",created_date);
        if(CondicionFecha!=null && !CondicionFecha.isEmpty())data.put("CondicionFecha",CondicionFecha);
        if(orden!=null && !orden.isEmpty())data.put("orden",orden);
        if(direccion!=null && !direccion.isEmpty())data.put("direccion",direccion);
        if(pageNumber != null) data.put("pageNumber",pageNumber);
        if(pageSize != null) data.put("pageSize",pageSize);
        return personaService.mayorQue(data);
    }

    @GetMapping("/menorQue")
    public Iterable<PersonaOutputDto> menorQue(@RequestParam(required = false) String usuario,
                                               @RequestParam(required = false) String name,
                                               @RequestParam(required = false) String surname,
                                               @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date created_date,
                                               @RequestParam(required = false) String CondicionFecha,
                                               @RequestParam(defaultValue = "usuario",required = false) String orden,
                                               @RequestParam(defaultValue = "asc", required = false) String direccion,
                                               @RequestParam(defaultValue = "0", required = false) Integer pageNumber,
                                               @RequestParam(defaultValue = "4", required = false) Integer pageSize) {
        HashMap<String, Object> data = new HashMap<>();
        if (usuario != null && !usuario.isEmpty()) data.put("usuario", usuario);
        if (name != null && !name.isEmpty()) data.put("name", name);
        if (surname != null && !surname.isEmpty()) data.put("surname", surname);
        if (created_date != null) data.put("created_date", created_date);
        if (CondicionFecha != null && !CondicionFecha.isEmpty()) data.put("CondicionFecha", CondicionFecha);
        if (orden != null && !orden.isEmpty()) data.put("orden", orden);
        if (direccion != null && !direccion.isEmpty()) data.put("direccion", direccion);
        if (pageNumber != null) data.put("pageNumber", pageNumber);
        if (pageSize != null) data.put("pageSize", pageSize);
        return personaService.menorQue(data);
    }
}