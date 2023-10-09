package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.controller.DTO.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.DTO.AsignaturaOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AsignaturaMapper {
    AsignaturaMapper instancia = Mappers.getMapper(AsignaturaMapper.class);

    Asignatura AsignaturaInputDtoToAsignatura(AsignaturaInputDto Asignatura);

    AsignaturaOutputDto AsignaturaToAsignaturaOutputDto(Asignatura p);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAsignaturaFromDto(AsignaturaInputDto Asignaturainput, @MappingTarget Asignatura entidad);
}
