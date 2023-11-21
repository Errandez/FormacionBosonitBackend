package com.example.block7crudvalidation.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Especifica los endpoints a los que se aplicará la configuración de CORS (en este caso, todos).
                .allowedOrigins("https://cdpn.io")  // Lista de dominios permitidos.
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos.
                .allowCredentials(true);  // Permite enviar cookies en las solicitudes.
    }
}
*/