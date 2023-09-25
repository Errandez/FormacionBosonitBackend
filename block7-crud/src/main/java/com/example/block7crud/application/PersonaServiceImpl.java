package com.example.block7crud.application;


import com.example.block7crud.controller.DTO.PersonaInputDto;
import com.example.block7crud.controller.DTO.PersonaOutputDto;
import com.example.block7crud.domain.Persona;
import com.example.block7crud.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService{

    @Autowired
    PersonaRepository PersonaRepository;

    @Override
    public PersonaOutputDto addPersona(PersonaInputDto persona) {
        return PersonaRepository.save(new Persona(persona)).PersonaToPersonaOutputDto();
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
    public PersonaOutputDto updatePersona(PersonaInputDto Persona) {
        PersonaRepository.findById(Persona.getId()).orElseThrow();
        return PersonaRepository.save(new Persona(Persona))
                .PersonaToPersonaOutputDto();
    }
}