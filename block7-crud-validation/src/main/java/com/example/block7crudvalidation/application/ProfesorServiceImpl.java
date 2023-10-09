package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.ProfesorInputDto;
import com.example.block7crudvalidation.controller.DTO.ProfesorOutputDto;
import com.example.block7crudvalidation.domain.Mappers.PersonaMapper;
import com.example.block7crudvalidation.domain.Mappers.ProfesorMapper;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.repository.PersonaRepository;
import com.example.block7crudvalidation.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return ProfesorMapper.instancia.ProfesorToProfesorOutputDto(ProfesorRepository.save(p));
    }

    @Override
    public ProfesorOutputDto getProfesorById(int id) {
        return ProfesorMapper.instancia
                .ProfesorToProfesorOutputDto(ProfesorRepository.findById(id).orElseThrow());
    }

    @Override
    public List<ProfesorOutputDto> getProfesorByName(String Name) {
        List<Profesor> Profesor = ProfesorRepository.findAll()
                .stream()
                .toList();
        List<ProfesorOutputDto> profesores = new ArrayList<>();
        for(Profesor p: Profesor){
            if(p.getPersona().getName().equalsIgnoreCase(Name)){
                ProfesorOutputDto aux = ProfesorMapper.instancia.ProfesorToProfesorOutputDto(p);
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
            profesores.add(ProfesorMapper.instancia.ProfesorToProfesorOutputDto(p));
        }
        return profesores;
    }

    @Override
    public ProfesorOutputDto updateProfesor(ProfesorInputDto Profesor) throws Exception {
        ProfesorRepository.findById(Profesor.getId_profesor()).orElseThrow();
        return ProfesorMapper.instancia.ProfesorToProfesorOutputDto(ProfesorRepository.save(ProfesorMapper.instancia.ProfesorInputDtoToProfesor(Profesor)));
    }
}
