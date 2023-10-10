package com.example.block7crudvalidation.application;

import com.example.block7crudvalidation.controller.DTO.Inputs.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.AsignaturaOutputDto;
import com.example.block7crudvalidation.domain.Mappers.AsignaturaMapper;
import com.example.block7crudvalidation.domain.Asignatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService{
    @Autowired
    com.example.block7crudvalidation.repository.AsignaturaRepository AsignaturaRepository;
    @Autowired
    com.example.block7crudvalidation.repository.PersonaRepository PersonaRepository;
    @Override
    public AsignaturaOutputDto addAsignatura(AsignaturaInputDto AsignaturaInput) throws Exception {


        Asignatura p = AsignaturaMapper.instancia.AsignaturaInputDtoToAsignatura(AsignaturaInput);

        return AsignaturaMapper.instancia.AsignaturaToAsignaturaOutputDto(AsignaturaRepository.save(p));
    }

    @Override
    public AsignaturaOutputDto getAsignaturaById(int id) {
        return AsignaturaMapper.instancia
                .AsignaturaToAsignaturaOutputDto(AsignaturaRepository.findById(id).orElseThrow());
    }



    @Override
    public void deleteAsignaturaById(int id) {
        Asignatura Asignatura = AsignaturaRepository.findById(id).orElseThrow();
        if(Asignatura!=null){
            AsignaturaRepository.deleteById(id);
        }
    }

    @Override
    public List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber,pageSize);
        List<AsignaturaOutputDto> Asignaturaes = new ArrayList<>();
        List<Asignatura> p1=AsignaturaRepository.findAll();
        for(Asignatura p: p1){
            Asignaturaes.add(AsignaturaMapper.instancia.AsignaturaToAsignaturaOutputDto(p));
        }
        return Asignaturaes;
    }

    @Override
    public AsignaturaOutputDto updateAsignatura(AsignaturaInputDto Asignatura) throws Exception {
        AsignaturaRepository.findById(Asignatura.getId_Asignatura()).orElseThrow();
        return AsignaturaMapper.instancia.AsignaturaToAsignaturaOutputDto(AsignaturaRepository.save(AsignaturaMapper.instancia.AsignaturaInputDtoToAsignatura(Asignatura)));
    }
}
