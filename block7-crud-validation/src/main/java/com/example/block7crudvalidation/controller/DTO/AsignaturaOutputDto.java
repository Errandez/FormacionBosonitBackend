package com.example.block7crudvalidation.controller.DTO;

import com.example.block7crudvalidation.domain.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsignaturaOutputDto {
    private int id_Asignatura;
    private Set<Student> students;
    private Date initial_date;
    private Date finish_date;
}
