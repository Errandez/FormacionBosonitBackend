package com.example.block5logging1;







import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Apartado1 {

    Logger logger = LoggerFactory.getLogger(Block5Logging1Application.class);

    @Bean
    public void ejecuta(){
        logger.trace("Mensaje 'trace'");
        logger.debug("Mensaje 'debug'");
        logger.info("Mensaje 'info'");
        logger.warn("Mensaje 'warning'");
        logger.error("Mensaje 'error'");
    }
}
