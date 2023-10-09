package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaStudentOutputDto;
import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import lombok.Builder;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.text.ParseException;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromDto(PersonaInputDto dto, @MappingTarget Persona entity);

    PersonaStudentOutputDto personaToEstudianteDto(Persona p);

    PersonaStudentOutputDto personaToProfesor(Persona p);

}

