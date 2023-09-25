package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.domain.CustomError;
import lombok.Data;
import org.springframework.http.ResponseEntity;

import java.util.Date;
@Data
public class UnprocessableEntityException extends Exception{
    private CustomError customError;

    public UnprocessableEntityException(String mensaje){
        CustomError aux = new CustomError();
        aux.setTimestamp(new Date());
        aux.setHttp(422);
        aux.setMensaje(mensaje);
        this.customError = aux;
    }
    public CustomError lanzar(){
        return this.customError;
    }
}
