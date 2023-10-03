package com.example.block7crudvalidation.controller.DTO;

import com.example.block7crudvalidation.domain.Student;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    private int id;


    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private String created_date;

    private String imagen_url;

    private String termination_date;

    private Student id_student;
}
