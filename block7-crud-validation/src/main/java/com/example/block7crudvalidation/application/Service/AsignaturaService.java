package com.example.block7crudvalidation.application.Service;

import com.example.block7crudvalidation.controller.DTO.Inputs.AsignaturaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.AsignaturaOutputDto;

import java.util.List;

public interface AsignaturaService {
    AsignaturaOutputDto addAsignatura(AsignaturaInputDto Asignatura) throws Exception;
    AsignaturaOutputDto getAsignaturaById(int id);

    void deleteAsignaturaById(int id);
    List<AsignaturaOutputDto> getAllAsignaturas(int pageNumber, int pageSize);
    AsignaturaOutputDto updateAsignatura (AsignaturaInputDto Asignatura) throws Exception;
}
