package com.example.block5profiles1;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class AppConfig {

    @Value("${bd.name}")
    private String name;

    @Value("${bd.url}")
    private String enviroment;
}
