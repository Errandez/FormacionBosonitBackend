package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaStudentOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    PersonaMapper INSTANCE = Mappers.getMapper(PersonaMapper.class);
    Persona PersonaInputDtoToPersona(PersonaInputDto persona);
    PersonaInputDto PersonaToPersonaInputDto(Persona persona);
    PersonaOutputDto PersonaToPersonaOutputDto(Persona persona);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePersonFromDto(PersonaInputDto dto, @MappingTarget Persona entity);

    PersonaStudentOutputDto personaToEstudianteDto(Persona p);

    PersonaStudentOutputDto personaToProfesor(Persona p);

}

