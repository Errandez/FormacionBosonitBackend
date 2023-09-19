package com.example.block5profiles1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Block5Profiles1Application implements CommandLineRunner {


    private final AppConfig url;

    public static void main(String[] args) {
        SpringApplication.run(Block5Profiles1Application.class, args);
    }



    @Override
    public void run(String... args) throws Exception {
            System.out.println("Estoy en " + url.getName() + " con URL " + url.getEnviroment());
    }
}
