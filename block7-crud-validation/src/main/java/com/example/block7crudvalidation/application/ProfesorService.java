package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.ProfesorOutputDto;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto Profesor) throws Exception;
    ProfesorOutputDto getProfesorById(int id);
    List<ProfesorOutputDto> getProfesorByName(String Name);
    void deleteProfesorById(int id);
    List<ProfesorOutputDto> getAllProfesors(int pageNumber, int pageSize);
    ProfesorOutputDto updateProfesor (ProfesorInputDto Profesor) throws Exception;
}
