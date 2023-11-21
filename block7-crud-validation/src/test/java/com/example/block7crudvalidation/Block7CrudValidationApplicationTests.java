package com.example.block7crudvalidation;

import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.List;

@SpringBootTest

class Block7CrudValidationApplicationTests {


    @Test
    void contextLoads() {
    }

}
