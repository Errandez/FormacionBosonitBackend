package com.example.block7crud.application;

import com.example.block7crud.controller.DTO.StudentInputDto;
import com.example.block7crud.controller.DTO.StudentOutputDto;

public interface StudentService {
    StudentOutputDto addStudent(StudentInputDto student);
    StudentOutputDto getStudentById(int id);
    void deleteStudentById(int id);
    Iterable<StudentOutputDto> getAllStudents(int pageNumber, int pageSize);
    StudentOutputDto updateStudent (StudentInputDto student);
}
