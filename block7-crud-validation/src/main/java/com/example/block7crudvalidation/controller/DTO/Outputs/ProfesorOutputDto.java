package com.example.block7crudvalidation.controller.DTO.Outputs;

import com.example.block7crudvalidation.domain.Persona;
import com.example.block7crudvalidation.domain.Student;
import com.example.block7crudvalidation.domain.branchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfesorOutputDto {
    private int id_profesor;
    private Persona persona;
    private branchType branch;
    private Set<Integer> id_students;
}
