package com.example.block6pathvariableheaders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controlador {

    // Petición POST 1.
    @PostMapping(value="/controlador/post1", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Object post1(@RequestBody String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Object general = om.readValue(json, Object.class);
        return general;
    }

    // Petición GET 1.
    @GetMapping(value="/user/{id}")
    public String get1(@PathVariable String id){
        return id;
    }


    // Petición PUT 1.
    @PutMapping(value="post")
    public HashMap<String, Integer> put1(@RequestParam int var1, @RequestParam int var2){
        HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
        hm1.put("var1",var1);
        hm1.put("var2",var2);
        return hm1;
    }

    // Petición GET 2.
    @GetMapping(value="/header")
    public String get1(@RequestHeader String h1, @RequestHeader String h2){
        return "Valor de h1: " + h1 + ", valor de h2: " + h2;
    }

    // Petición POST 2.
    @PostMapping(value="/all")
    public objectoGenerico getAll(@RequestBody(required = false) String json, @RequestParam Map<String, String> requestParams,
                         @RequestHeader Map<String, String> headers){
        objectoGenerico og = new objectoGenerico();
        og.setBody(json);
        og.setHeaders(new ArrayList<>(headers.values()));
        og.setRequestParams(new ArrayList<>(requestParams.values()));
        return og;
    }

}
