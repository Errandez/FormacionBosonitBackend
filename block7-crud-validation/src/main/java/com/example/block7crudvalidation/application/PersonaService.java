package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaOutputDto;

import java.util.List;

public interface PersonaService {
    PersonaOutputDto addPersona(PersonaInputDto Persona) throws Exception;
    PersonaOutputDto getPersonaById(int id);
    List<PersonaOutputDto> getPersonaByName(String Name);
    void deletePersonaById(int id);
    List<PersonaOutputDto> getAllPersonas(int pageNumber, int pageSize);
    PersonaOutputDto updatePersona (PersonaInputDto Persona) throws Exception;
}
