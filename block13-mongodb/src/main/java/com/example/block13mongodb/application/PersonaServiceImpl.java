package com.example.block13mongodb.application;


import com.example.block13mongodb.controller.DTO.PersonaInputDto;
import com.example.block13mongodb.controller.DTO.PersonaOutputDto;
import com.example.block13mongodb.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonaServiceImpl implements PersonaService {

    private final MongoTemplate mongoTemplate;



    @Autowired
    public PersonaServiceImpl(MongoTemplate m){
        this.mongoTemplate = m;
    }

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return mongoTemplate.save(new Persona(persona)).PersonaToPersonaOutputDto();
    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        Query q = new Query();
        q.addCriteria(Criteria.where("persona").is(id));
        return mongoTemplate.findOne(q, Persona.class).PersonaToPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonaByName(String Name) {
        Query q = new Query();
        q.addCriteria(Criteria.where("nombre").is(Name));
        List<Persona> personas = mongoTemplate.find(q, Persona.class);
        List<PersonaOutputDto> personaOutputDtos = new ArrayList<>();
        for(Persona p: personas){
            personaOutputDtos.add(p.PersonaToPersonaOutputDto());
        }
        return personaOutputDtos;
    }

    @Override
    public void deletePersonaById(int id) {
        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(id));
        Persona persona = mongoTemplate.findOne(q,Persona.class);
        mongoTemplate.remove(persona);
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        Query q = new Query();
        q.skip(pageNumber * pageSize);
        q.limit(pageSize);
        List<Persona> personas = mongoTemplate.find(q, Persona.class);
        List<PersonaOutputDto> personaOutputDtos = new ArrayList<>();
        for(Persona p: personas){
            personaOutputDtos.add(p.PersonaToPersonaOutputDto());
        }
        return personaOutputDtos;
    }
    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto Persona) {
        Persona persona = new Persona(Persona);
        return mongoTemplate.save(persona).PersonaToPersonaOutputDto();
    }
}