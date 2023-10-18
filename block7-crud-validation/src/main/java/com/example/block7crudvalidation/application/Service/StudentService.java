package com.example.block7crudvalidation.application.Service;

import com.example.block7crudvalidation.controller.DTO.Inputs.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto2;

import java.util.List;

public interface StudentService {
    StudentOutputDto2 addStudent(StudentInputDto Student) throws Exception;
    StudentOutputDto getStudentById(int id);
    List<StudentOutputDto> getStudentByName(String Name);
    void deleteStudentById(int id);
    List<StudentOutputDto2> getAllStudents(int pageNumber, int pageSize);

    StudentOutputDto addAsignatura(int id,List<Integer> lista);

    StudentOutputDto updateStudent (StudentInputDto Student) throws Exception;
}
