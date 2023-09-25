package com.example.block7crud.application;

import com.example.block7crud.controller.DTO.PersonaInputDto;
import com.example.block7crud.controller.DTO.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto Persona);
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getPersonaByName(String Name);
    void deletePersonaById(int id);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona (PersonaInputDto Persona);
}
