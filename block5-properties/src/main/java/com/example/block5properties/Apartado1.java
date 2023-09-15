package com.example.block5properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class Apartado1 {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private Integer number;

    @Value("${new.property:new.property no tiene valor}")
    private String property;



    @Bean
    public void Apartado1(){
        System.out.println("El valor de greeting es: ("+greeting+")");
        System.out.println("El valor de my.number es: ("+number+")");
        System.out.println("El valor de new.property es: ("+property+")");
        //Dentro del contexto Java.
        System.setProperty("new.property",property);
        //Dentro del SO.
        try{

            String comando = "set " + "new.property" + " " + property;


            ProcessBuilder processBuilder = new ProcessBuilder("cmd", "/c", comando);
            Process proceso = processBuilder.start();

            int exitCode = proceso.waitFor();

            if(exitCode==0){
                System.out.println("Variable de entorno añadida con éxito.");
            }
            else{
                System.out.println("No se pudo añadir la variable de entorno.");
            }

        }catch(IOException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }


}
