package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.application.AsignaturaServiceImpl;
import com.example.block7crudvalidation.controller.DTO.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.DTO.AsignaturaOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Asignatura")
public class ControllerAsignatura {

    @Autowired
    AsignaturaServiceImpl AsignaturaService;

    @PostMapping
    public ResponseEntity<AsignaturaOutputDto> addAsignatura(@RequestBody AsignaturaInputDto json) throws Exception {
        URI location = URI.create("/Asignatura");
        return ResponseEntity.created(location).body(AsignaturaService.addAsignatura(json));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaOutputDto> getAsignaturaById(@PathVariable int id){
        try{
            return ResponseEntity.ok().body(AsignaturaService.getAsignaturaById(id));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteAsignaturaById(@PathVariable int id) {
        try {
            AsignaturaService.deleteAsignaturaById(id);
            return ResponseEntity.ok().body("Asignatura with id: " + id + " was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();

        }
    }
    @GetMapping
    public Iterable<AsignaturaOutputDto> getAllAsignaturas(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return AsignaturaService.getAllAsignaturas(pageNumber, pageSize);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<AsignaturaOutputDto> updateAsignatura(@PathVariable int id,@RequestBody AsignaturaInputDto json) {
        try {
            AsignaturaService.updateAsignatura(json);
            return  ResponseEntity.ok().body(AsignaturaService.addAsignatura(json));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
