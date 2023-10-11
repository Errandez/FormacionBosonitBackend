package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.Inputs.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.ProfesorOutputDto;
import com.example.block7crudvalidation.domain.Mappers.ProfesorMapper;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProfesorServiceImpl implements ProfesorService{
    @Autowired
    com.example.block7crudvalidation.repository.ProfesorRepository ProfesorRepository;
    @Autowired
    com.example.block7crudvalidation.repository.PersonaRepository PersonaRepository;
    @Override
    public ProfesorOutputDto addProfesor(ProfesorInputDto profesorInput) throws Exception {

        Persona persona = PersonaRepository.findById(profesorInput.getId_persona()).orElseThrow();
        Profesor p = ProfesorMapper.instancia.ProfesorInputDtoToProfesor(profesorInput);
        p.setPersona(persona);
        return this.profesorToProfesorOutputDto(ProfesorRepository.save(p));
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
        ProfesorMapper.instancia.updateProfesorFromDto(profesorInputDto,profesor);
        Profesor p1 = ProfesorRepository.save(profesor);
        return this.profesorToProfesorOutputDto(p1);
    }

    @Override
    public ProfesorOutputDto addStudentToProfesor(Profesor profesorInputDto, Student student) throws UnprocessableEntityException {
        Profesor profesor= ProfesorRepository.findById(profesorInputDto.getProfesor()).orElseThrow();
        Persona persona = PersonaRepository.findById(profesorInputDto.getPersona().getPersona()).orElseThrow();

        if(persona.getProfesor()==null && persona.getStudent()==null){
            Set<Student> students = profesor.getStudents();
            students.add(student);
            profesor.setStudents(students);
            ProfesorInputDto profesorAux = ProfesorMapper.instancia.ProfesorToProfesorInputDto(profesorInputDto);
            ProfesorMapper.instancia.updateProfesorFromDto(profesorAux,profesor);
            profesor.setPersona(persona);
            profesor.setStudents(students);
            Profesor p1 = ProfesorRepository.save(profesor);
            ProfesorRepository.save(profesor);
            return this.profesorToProfesorOutputDto(p1);
        }else{
            throw new UnprocessableEntityException("Persona ya asignada.");

        }

    }
    public ProfesorOutputDto profesorToProfesorOutputDto(Profesor profesor){
        Set<Integer> students = new HashSet<>();
        if(profesor.getStudents()!=null) {
            for (Student s : profesor.getStudents()) {
                students.add(s.getStudent());
            }
        }
        ProfesorOutputDto profesorOutputDto = new ProfesorOutputDto(profesor.getProfesor(), profesor.getPersona(),profesor.getBranch(),students);
        return profesorOutputDto;
    }
}
