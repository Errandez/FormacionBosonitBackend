package com.example.bloque5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Apartado1 {



    @Bean
    public void ejecuta(){
        Logger logger = LoggerFactory.getLogger(Bloque5LoggingApplication.class);
        logger.error("Mensaje 'error'");
        logger.warn("Mensaje 'warning'");
        logger.info("Mensaje 'info'");
    }
}
