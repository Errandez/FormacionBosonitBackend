package com.example.block7crudvalidation.controller.DTO.Inputs;

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
public class ProfesorInputDto {
    private int id_profesor;
    private Integer id_persona;
    private branchType branch;

}
