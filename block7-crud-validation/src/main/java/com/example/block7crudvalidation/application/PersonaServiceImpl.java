package com.example.block7crudvalidation.application;


import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.UnrecoverableEntryException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements com.example.block7crudvalidation.application.PersonaService{

    @Autowired
    PersonaRepository PersonaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) throws UnprocessableEntityException, Exception {


            Persona p = new Persona(persona);
            if(p!=null) {
                return PersonaRepository.save(p).PersonaToPersonaOutputDto();
            }throw new UnprocessableEntityException("");

    }

    @Override
    public PersonaOutputDto getPersonaById(int id) {
        return PersonaRepository.findById(id).orElseThrow()
                .PersonaToPersonaOutputDto();
    }

    @Override
    public List<PersonaOutputDto> getPersonaByName(String Name) {
        List<PersonaOutputDto> personas =  PersonaRepository.findAll()
                .stream()
                .map(Persona::PersonaToPersonaOutputDto).toList();
        List<PersonaOutputDto> personasFinal = new ArrayList<>();
        for(PersonaOutputDto paux:personas){
            if(paux.getName().equalsIgnoreCase(Name)){
                personasFinal.add(paux);
            }
        }
        return personasFinal;
    }

    @Override
    public void deletePersonaById(int id) {
        PersonaRepository.findById(id).orElseThrow();
        PersonaRepository.deleteById(id);
    }
    @Override
    public List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return PersonaRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Persona::PersonaToPersonaOutputDto).toList();
    }


    @Override
    public PersonaOutputDto updatePersona(PersonaInputDto Persona) throws Exception {
        PersonaRepository.findById(Persona.getId()).orElseThrow();
        return PersonaRepository.save(new Persona(Persona))
                .PersonaToPersonaOutputDto();
    }
}