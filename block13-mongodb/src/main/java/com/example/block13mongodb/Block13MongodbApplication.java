package com.example.block13mongodb;

import com.example.block13mongodb.application.PersonaServiceImpl;
import com.example.block13mongodb.controller.DTO.PersonaInputDto;
import com.example.block13mongodb.domain.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class Block13MongodbApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger("AppsDeveloperBlog");
    private final PersonaServiceImpl personaServiceImpl;

    @Autowired
    public Block13MongodbApplication(PersonaServiceImpl personaServiceImpl) {
        this.personaServiceImpl = personaServiceImpl;
    }
    public static void main(String[] args) {
        SpringApplication.run(Block13MongodbApplication.class, args);
    }
    @Override
    public void run(String... args) {
        personaServiceImpl.addPersona(new PersonaInputDto(
                1, "Sergey", "Alfaro", 21));
        personaServiceImpl.addPersona(new PersonaInputDto(
                1, "Erick", "Alfaro", 21));
        personaServiceImpl.addPersona(new PersonaInputDto(
                1, "Sergey", "Alfaro", 23));
        personaServiceImpl.addPersona(new PersonaInputDto(
                1, "Irati", "Alfaro", 25));
        personaServiceImpl.addPersona(new PersonaInputDto(
                1, "Sergey", "Alfaro", 22));
        LOG.info("Getting all data from MongoDB: \n{}",
                personaServiceImpl.getAllPersonas(5,4));
        LOG.info("Getting Persona By name 'Sergey': {}",
                personaServiceImpl.getPersonaByName("Sergey"));

    }
}
