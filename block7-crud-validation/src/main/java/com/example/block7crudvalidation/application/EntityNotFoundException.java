package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.domain.CustomError;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Date;
@Data
public class EntityNotFoundException extends Exception {
    private CustomError customError;

    public EntityNotFoundException(String mensaje){
        CustomError aux = new CustomError();
        aux.setTimestamp(new Date());
        aux.setHttp(404);
        aux.setMensaje(mensaje);
        this.customError = aux;
    }
    public CustomError lanzar(){
        return this.customError;
    }
}
