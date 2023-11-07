package com.example.block11uploaddownloadfiles.application;

import com.example.block11uploaddownloadfiles.domain.DTO.FicheroOutputDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FicheroService {
    FicheroOutputDto addFichero(String tipo, MultipartFile fichero) throws IOException;
    String modificarRuta(String ruta);
    FicheroOutputDto descargarFicheroId(int id) throws IOException;
    FicheroOutputDto descargarFicheroNombre(String nombre) throws IOException;

}
