package com.example.block13mongodb.application;

import com.example.block13mongodb.controller.DTO.PersonaInputDto;
import com.example.block13mongodb.controller.DTO.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto Persona);
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getPersonaByName(String Name);
    void deletePersonaById(int id);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona (PersonaInputDto Persona);
}
