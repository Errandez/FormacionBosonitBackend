package com.example.block6personcontrollers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {


    @Autowired
    @Qualifier("getBean1")
    private Persona bean1;

    @Autowired
    @Qualifier("getBean2")
    private Persona bean2;

    @Autowired
    @Qualifier("getBean3")
    private Persona bean3;

    // Apartado 3.
    @GetMapping(value="/controlador/bean/{bean}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Persona getBean(@PathVariable String bean){
        if(bean.equalsIgnoreCase("bean1")){
            return bean1;
        }else if(bean.equalsIgnoreCase("bean2")){
            return bean2;
        }else if(bean.equalsIgnoreCase("bean3")){
            return bean3;
        }
        return null;
    }

}
