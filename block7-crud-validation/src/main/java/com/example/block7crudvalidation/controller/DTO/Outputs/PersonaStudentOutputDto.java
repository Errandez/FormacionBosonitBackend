package com.example.block7crudvalidation.controller.DTO.Outputs;

import com.example.block7crudvalidation.domain.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaStudentOutputDto {

    private int id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private boolean active;
    private StudentOutputDto studentOutputDto;

}