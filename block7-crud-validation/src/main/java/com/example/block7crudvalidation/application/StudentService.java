package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.StudentOutputDto;

import java.util.List;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto Student) throws Exception;
    StudentOutputDto getStudentById(int id);
    List<StudentOutputDto> getStudentByName(String Name);
    void deleteStudentById(int id);
    List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);

    StudentOutputDto updateStudent (StudentInputDto Student) throws Exception;
}
