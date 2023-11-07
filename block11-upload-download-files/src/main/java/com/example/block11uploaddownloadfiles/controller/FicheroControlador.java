package com.example.block11uploaddownloadfiles.controller;

import com.example.block11uploaddownloadfiles.application.FicheroService;
import com.example.block11uploaddownloadfiles.domain.DTO.FicheroOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/Fichero")
public class FicheroControlador {

    @Autowired
    FicheroService ficheroService;

    @PostMapping("/upload/{tipo}")
    public FicheroOutputDto subirFichero(@PathVariable String tipo, @RequestParam("file")MultipartFile fichero) throws IOException {
        URI location = URI.create("/Fichero");
        return ficheroService.addFichero(tipo, fichero);
    }

    @GetMapping("/setPath")
    public String cambiarPath(@RequestParam("Path") String ruta){
        return ficheroService.modificarRuta(ruta);
    }

    @GetMapping("/id/{id}")
    public FicheroOutputDto descargarFicheroId(@PathVariable int id) throws IOException {
        return ficheroService.descargarFicheroId(id);
    }

    @GetMapping("/nombre/{nombre}")
    public FicheroOutputDto descargarFicheroNombre(@PathVariable String nombre) throws IOException {
        return ficheroService.descargarFicheroNombre(nombre);
    }

}
