package com.example.block7crudvalidation.domain.Mappers;


import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.domain.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentMapper instancia = Mappers.getMapper(StudentMapper.class);
    Student StudentInputDtoToStudent(StudentInputDto student);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStudentFromDto(StudentInputDto studentinput, @MappingTarget Student entidad);
}
