package com.example.block7crudvalidation.controller.DTO;

import com.example.block7crudvalidation.domain.Asignatura;
import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Profesor;
import com.example.block7crudvalidation.domain.branchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInputDto {
    private int id_student;
    private int id_persona;
    private Integer num_hours_week;
    private Integer id_profesor;
    private branchType branch;
}
