package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.StudentOutputDto;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;

import com.example.block7crudvalidation.domain.Mappers.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    com.example.block7crudvalidation.repository.StudentRepository StudentRepository;
    @Autowired
    com.example.block7crudvalidation.repository.PersonaRepository PersonaRepository;

    @Override
    public StudentOutputDto addStudent(StudentInputDto student) throws Exception{
        Persona persona = PersonaRepository.findById(student.getId_persona()).orElseThrow();
        Student student1 = StudentMapper.instancia.StudentInputDtoToStudent(student);
        student1.setId_persona(persona);

        return StudentRepository.save(student1).StudentToStudentOutputDto();

    }

    @Override
    public StudentOutputDto getStudentById(String id) {
        return StudentRepository.findById(id).orElseThrow()
                .StudentToStudentOutputDto();
    }

    @Override
    public List<StudentOutputDto> getStudentByName(String Name) {
        List<StudentOutputDto> Students =  StudentRepository.findAll()
                .stream()
                .map(Student::StudentToStudentOutputDto).toList();
        List<StudentOutputDto> StudentsFinal = new ArrayList<>();
        for(StudentOutputDto paux:Students){
            if(paux.getId_persona().getName().equalsIgnoreCase(Name)){
                StudentsFinal.add(paux);
            }
        }
        return StudentsFinal;
    }

    @Override
    public void deleteStudentById(String id) {
        StudentRepository.findById(id).orElseThrow();
        StudentRepository.deleteById(id);
    }
    @Override
    public List<StudentOutputDto> getAllStudents(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return StudentRepository.findAll(pageRequest).getContent()
                .stream()
                .map(Student::StudentToStudentOutputDto).toList();
    }
    @Override
    public StudentOutputDto updateStudent(StudentInputDto Student) {
        StudentRepository.findById(Student.getId_student()).orElseThrow();
        return StudentRepository.save(new Student(Student))
                .StudentToStudentOutputDto();
    }
}
