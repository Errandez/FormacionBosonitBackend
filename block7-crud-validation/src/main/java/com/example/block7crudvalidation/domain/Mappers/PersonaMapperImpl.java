package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.application.PersonaServiceImpl;
import com.example.block7crudvalidation.application.ProfesorServiceImpl;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
/*
public class PersonaMapperImpl {

    StudentMapperImpl studentService;

    PersonaMapperImpl personaService;
    public PersonaOutputDto PersonaToPersonaOutput(Persona persona){
        StudentOutputDto studentOutputDto = studentService.StudentToStudentOutputDto(persona.getStudent());
        PersonaOutputDto personaOutputDto = new PersonaOutputDto(
                persona.getPersona(),
                persona.getUsuario(), persona.getPassword(), persona.getName(), persona.getSurname(), persona.getCompany_email(),
                persona.getPersonal_email(), persona.getCity(),persona.getActive(),persona.getCreated_date(), persona.getImagen_url(),
                persona.getTermination_date(), studentOutputDto
        );
        return personaOutputDto;
    }
}
*/

