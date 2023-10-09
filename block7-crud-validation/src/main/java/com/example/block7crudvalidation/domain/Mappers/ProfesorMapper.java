package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.controller.DTO.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.ProfesorOutputDto;
import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfesorMapper {
    ProfesorMapper instancia = Mappers.getMapper(ProfesorMapper.class);

    Profesor ProfesorInputDtoToProfesor(ProfesorInputDto Profesor);

    ProfesorOutputDto ProfesorToProfesorOutputDto(Profesor p);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfesorFromDto(ProfesorInputDto Profesorinput, @MappingTarget Profesor entidad);
}
