package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.ProfesorServiceImpl;
import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.ProfesorOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Profesor")
public class ControllerProfesor {
    @Autowired
    ProfesorServiceImpl ProfesorService;

    @PostMapping
    public ResponseEntity<ProfesorOutputDto> addProfesor(@RequestBody ProfesorInputDto json) throws Exception {
        URI location = URI.create("/Profesor");
        return ResponseEntity.created(location).body(ProfesorService.addProfesor(json));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorOutputDto> getProfesorById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(ProfesorService.getProfesorById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<ProfesorOutputDto>> getProfesorByName(@PathVariable String nombre){
        try{
            return ResponseEntity.ok().body(ProfesorService.getProfesorByName(nombre));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteProfesorById(@PathVariable int id) {
        try {
            ProfesorService.deleteProfesorById(id);
            return ResponseEntity.ok().body("Profesor with id: " + id + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping
    public Iterable<ProfesorOutputDto> getAllProfesors(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return ProfesorService.getAllProfesors(pageNumber, pageSize);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ProfesorOutputDto> updateProfesor(@PathVariable int id,@RequestBody ProfesorInputDto json) {
        try {
            ProfesorService.updateProfesor(json);
            return  ResponseEntity.ok().body(ProfesorService.addProfesor(json));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
