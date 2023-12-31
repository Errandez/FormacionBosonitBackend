package com.example.block7crudvalidation.application.ServiceImpl;

import com.example.block7crudvalidation.application.Service.ProfesorService;
import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.ProfesorOutputDto;
import com.example.block7crudvalidation.domain.Mappers.ProfesorMapper;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    com.example.block7crudvalidation.repository.ProfesorRepository ProfesorRepository;
    @Autowired
    com.example.block7crudvalidation.repository.PersonaRepository PersonaRepository;
    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInput) throws Exception {


        Persona persona = PersonaRepository.findById(profesorInput.getId_persona()).orElseThrow();
        Profesor p = ProfesorMapper.INSTANCE.profesorInputDtoToProfesor(profesorInput);
        if (persona.getProfesor() != null && persona.getStudent()!=null)throw new NoSuchElementException("Esta persona ya está asignada.");


        p.setPersona(persona);
        return (ProfesorRepository.save(p).ProfesorToProfesorOutputDto());
    }

    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        return this.profesorToProfesorOutputDto(ProfesorRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProfesorOutputDto> getProfesorByName(String Name) {
        List<Profesor> Profesor = ProfesorRepository.findAll()
                .stream()
                .toList();
        List<ProfesorOutputDto> profesores = new ArrayList<>();
        for(Profesor p: Profesor){
            if(p.getPersona().getName().equalsIgnoreCase(Name)){
                ProfesorOutputDto aux = this.profesorToProfesorOutputDto(p);
                profesores.add(aux);
            }
        }
        return profesores;
    }

    @Override
    public void deleteProfesorById(int id) {
        Profesor profesor = ProfesorRepository.findById(id).orElseThrow();
        if(profesor!=null){
            ProfesorRepository.deleteById(id);
        }
    }

    @Override
    public List<ProfesorOutputDto> getAllProfesors(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        List<ProfesorOutputDto> profesores = new ArrayList<>();
        List<Profesor> p1=ProfesorRepository.findAll();
        for(Profesor p: p1){
            profesores.add(this.profesorToProfesorOutputDto(p));
        }
        return profesores;
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto profesorInputDto) throws Exception {
        Profesor profesor=ProfesorRepository.findById(profesorInputDto.getId_profesor()).orElseThrow();
        ProfesorMapper.INSTANCE.updateProfesorFromDto(profesorInputDto,profesor);
        Profesor p1 = ProfesorRepository.save(profesor);
        return this.profesorToProfesorOutputDto(p1);
    }

    @Override
    public ProfesorOutputDto addStudentToProfesor(ProfesorInputDto profesorInputDto, Student student){
        Profesor profesor=ProfesorRepository.findById(profesorInputDto.getId_profesor()).orElseThrow();
        Set<Student> students = profesor.getStudents();
        students.add(student);

        profesor.setStudents(students);
        ProfesorMapper.INSTANCE.updateProfesorFromDto(profesorInputDto,profesor);
        profesor.setStudents(students);
        Profesor p1 = ProfesorRepository.save(profesor);
        ProfesorRepository.save(profesor);
        return this.profesorToProfesorOutputDto(p1);
    }
    public ProfesorOutputDto profesorToProfesorOutputDto(Profesor profesor){
        Set<Integer> students = new HashSet<>();
        if(profesor.getStudents()!=null) {
            for (Student s : profesor.getStudents()) {
                students.add(s.getStudent());
            }
        }
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor.getId_profesor(), profesor.getPersona().personToPersonOutputDto(),profesor.getBranch(),profesor.getStudents().stream().map(Student::studentToStudentOutputDto2).collect(Collectors.toSet()));
        return profesorOutputDto;
    }
}
