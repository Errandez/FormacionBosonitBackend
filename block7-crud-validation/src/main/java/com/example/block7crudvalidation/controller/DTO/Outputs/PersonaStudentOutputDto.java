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

    private Integer id_persona;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;

    private Student id_student;

    private int num_hours_week;

    private ProfesorOutputDto id_profesor;

    private branchType branch;

    private Set<AsignaturaOutputDto> asignaturas;

}
