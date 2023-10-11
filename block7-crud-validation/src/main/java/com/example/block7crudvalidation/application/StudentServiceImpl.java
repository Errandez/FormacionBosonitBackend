package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Inputs.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Mappers.ProfesorMapper;
import com.example.block7crudvalidation.domain.Mappers.StudentMapper;
import com.example.block7crudvalidation.domain.Mappers.StudentMapperImpl;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    com.example.block7crudvalidation.repository.StudentRepository StudentRepository;
    @Autowired
    com.example.block7crudvalidation.repository.PersonaRepository PersonaRepository;
    @Autowired
    com.example.block7crudvalidation.repository.ProfesorRepository ProfesorRepository;
    @Autowired
    com.example.block7crudvalidation.repository.AsignaturaRepository AsignaturaRepository;
    @Autowired
    ProfesorServiceImpl profesorService;
    @Autowired
    PersonaServiceImpl personaService;




    @Override
    public StudentOutputDto addStudent(StudentInputDto student) throws Exception{

        Persona persona = PersonaRepository.findById(student.getId_persona()).orElseThrow();
        Profesor profesor = ProfesorRepository.findById(student.getId_profesor()).orElseThrow();
        Student student1 = StudentMapper.instancia.StudentInputDtoToStudent(student);


        if(persona.getStudent()==null && persona.getProfesor()==null) {
            student1.setPersona(persona);
            student1.setProfesor(profesor);
            ProfesorInputDto profesorInputDto = ProfesorMapper.instancia.ProfesorToProfesorInputDto(profesor);
            Student student2 = null;
            profesorInputDto.setId_profesor(profesor.getProfesor());
            student2 = StudentRepository.save(student1);
            profesorService.addStudentToProfesor(profesor,student2);
            return (student2).StudentToStudentOutputDto();
        }
        else{
            return null;
        }

    }

    @Override
    public StudentOutputDto getStudentById(int id) {
        return (StudentRepository.findById(id).orElseThrow()).StudentToStudentOutputDto();
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
    public void deleteStudentById(int id) {
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
    public StudentOutputDto addAsignatura(int id,List<Integer> lista) {
        Student s = StudentRepository.findById(id).orElseThrow();
        Set<Asignatura> asignaturaSet = s.getAsignaturas();
        for(Integer i:lista){
            Asignatura a = AsignaturaRepository.findById(i).orElseThrow();
            asignaturaSet.add(a);
        }
        s.setAsignaturas(asignaturaSet);
        StudentRepository.save(s);
        return s.StudentToStudentOutputDto();
    }

    @Override
    public StudentOutputDto updateStudent(StudentInputDto Student) {
        StudentRepository.findById(Student.getId_student()).orElseThrow();
        return (StudentRepository.save(new Student(Student))).StudentToStudentOutputDto();
    }


}
