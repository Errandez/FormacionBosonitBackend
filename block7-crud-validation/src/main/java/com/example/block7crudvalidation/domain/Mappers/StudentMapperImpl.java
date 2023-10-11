package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.application.ProfesorServiceImpl;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.example.block7crudvalidation.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
/*
public class StudentMapperImpl {
    @Autowired
    ProfesorServiceImpl profesorService;
    @Autowired
    PersonaServiceImpl personaService;
    @Autowired
    PersonaMapperImpl personaMapper;
    public StudentOutputDto StudentToStudentOutputDto(Student student){
        StudentOutputDto studentOutputDto = new StudentOutputDto(
                student.getStudent(),
                personaMapper.PersonaToPersonaOutput(student.getPersona()),
                student.getNum_hours_week(),
                profesorService.profesorToProfesorOutputDto(student.getProfesor()),
                student.getBranch(),
                student.getAsignaturas()
        );
        return studentOutputDto;
    }
}
*/