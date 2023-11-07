package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.Repository.FicheroRepository;
import com.example.block11uploaddownloadfiles.domain.DTO.FicheroOutputDto;
import com.example.block11uploaddownloadfiles.domain.Fichero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Optional;

@Service
public class FicheroServiceImpl implements FicheroService, CommandLineRunner {
    private String ruta = System.getProperty("user.dir");
    private String directorio="block11-upload-download-files/Ficheros";
    @Autowired
    FicheroRepository ficheroRepository;


    @Override
    public void run(String... args) throws Exception {
        this.ruta=args.length>0 ? args[0] : System.getProperty("user.dir");
    }
    @Override
    public FicheroOutputDto addFichero(String tipo, MultipartFile fichero) throws IOException {
        if (!fichero.isEmpty() && fichero.getOriginalFilename().endsWith(tipo)) {
            String nombre = fichero.getOriginalFilename();

            File path = new File(this.directorio);
            Path filePath = Paths.get(this.directorio).resolve(nombre);
            Files.write(filePath, fichero.getBytes());

            // Guardar en la base de datos
            Fichero fichero1 = new Fichero();
            fichero1.setNombre(nombre);
            fichero1.setFechaSubida(new Date(System.currentTimeMillis()));
            fichero1.setCategoria(tipo);
            return ficheroRepository.save(fichero1).FicheroToFicheroOutputDto();
        }else{
            throw new IllegalArgumentException("El fichero no es válido.");
        }
    }

    @Override
    @GetMapping("/setpath/{dir}")
    public String modificarRuta(String ruta) {
        Path rutaaux = Paths.get(ruta);
        if(Files.exists(rutaaux)){
            this.ruta = ruta;
            return this.ruta;
        }else{
            throw new IllegalArgumentException("La ruta especificada no existe.");
        }
    }

    @Override
    public FicheroOutputDto descargarFicheroId(int id) throws IOException {
        Optional<Fichero> posibleFichero = ficheroRepository.findById(id);
        if(!posibleFichero.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No se ha encontrado el fichero con el id " + id + ".");
        }
        Fichero f = posibleFichero.get();
        String rutaFichero = this.directorio + "/" + f.getNombre();
        System.out.println(rutaFichero);
        Path p = Paths.get(rutaFichero);
        if(!Files.exists(p) || Files.isDirectory(p)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No se ha encontrado el fichero en la ruta indicada.");
        }
        try{
            byte[] contenido = Files.readAllBytes(p);
            String nombreFichero = f.getNombre();
            Path rutaFicheroActual = Paths.get(this.ruta).resolve(nombreFichero);
            Files.write(rutaFicheroActual,contenido);
            return f.FicheroToFicheroOutputDto();
        }catch(IOException ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public FicheroOutputDto descargarFicheroNombre(String nombre) throws IOException {
        Optional<Fichero> posibleFichero = ficheroRepository.findByNombre(nombre);
        if(!posibleFichero.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No se ha encontrado el fichero con el nombre " + nombre + ".");
        }
        Fichero f = posibleFichero.get();
        String rutaFichero = this.directorio + "/" + f.getNombre();
        System.out.println(rutaFichero);
        Path p = Paths.get(rutaFichero);
        if(!Files.exists(p) || Files.isDirectory(p)){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"No se ha encontrado el fichero en la ruta indicada.");
        }
        try{
            byte[] contenido = Files.readAllBytes(p);
            String nombreFichero = f.getNombre();
            Path rutaFicheroActual = Paths.get(this.ruta).resolve(nombreFichero);
            Files.write(rutaFicheroActual,contenido);
            return f.FicheroToFicheroOutputDto();
        }catch(IOException ex){
            ex.printStackTrace();
            throw ex;
        }
    }
}
