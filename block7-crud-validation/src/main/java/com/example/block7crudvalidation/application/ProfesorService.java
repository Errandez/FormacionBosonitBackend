package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.ProfesorOutputDto;
import com.example.block7crudvalidation.domain.Student;

import java.util.List;

public interface ProfesorService {
    ProfesorOutputDto addProfesor(ProfesorInputDto Profesor) throws Exception;
    ProfesorOutputDto getProfesorById(int id);
    List<ProfesorOutputDto> getProfesorByName(String Name);
    void deleteProfesorById(int id);
    List<ProfesorOutputDto> getAllProfesors(int pageNumber, int pageSize);
    ProfesorOutputDto updateProfesor (ProfesorInputDto Profesor) throws Exception;
    ProfesorOutputDto addStudentToProfesor(ProfesorInputDto profesorInputDto, Student student);
}
