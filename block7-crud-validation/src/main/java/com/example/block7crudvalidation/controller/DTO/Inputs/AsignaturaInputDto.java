package com.example.block7crudvalidation.controller.DTO.Inputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaInputDto {
    private Integer id_Asignatura;
    private Date initial_date;
    private Date finish_date;
}
