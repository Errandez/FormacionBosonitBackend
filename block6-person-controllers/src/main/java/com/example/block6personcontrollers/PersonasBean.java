package com.example.block6personcontrollers;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class PersonasBean {
    @Bean
    public Persona getBean1(){
        Persona p = new Persona();
        p.setNombre("bean1");
        return p;
    }

    @Bean
    public Persona getBean2(){
        Persona p = new Persona();
        p.setNombre("bean2");
        return p;
    }

    @Bean
    public Persona getBean3(){
        Persona p = new Persona();
        p.setNombre("bean3");
        return p;
    }
}
