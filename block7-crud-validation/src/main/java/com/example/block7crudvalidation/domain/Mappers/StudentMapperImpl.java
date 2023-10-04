package com.example.block7crudvalidation.domain.Mappers;

import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentMapperImpl implements StudentMapper{
    @Autowired
    com.example.block7crudvalidation.repository.StudentRepository StudentRepository;
    @Override
    public Student StudentInputDtoToStudent(StudentInputDto student) {
        Student student1= StudentRepository.findById(student.getId_student()).orElseThrow();
        student1.setId_student(student.getId_student());
        student1.setBranch(student.getBranch());
        student1.setAsignaturas(student.getAsignaturas());
        student1.setNum_hours_week(student.getNum_hours_week());
        return student1;
    }

    @Override
    public void updateStudentFromDto(StudentInputDto studentinput, Student entidad) {

    }
}
