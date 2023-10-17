package com.example.block7crudvalidation.domain;


import com.example.block7crudvalidation.application.UnprocessableEntityException;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaProfesorOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaStudentOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name="Persona")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona {
    @Id
    @GeneratedValue
    private int persona;
    private String usuario;
    private String password;
    private String name;
    private String surname;
    private String company_email;
    private String personal_email;
    private String city;
    private Boolean active;
    private java.sql.Date created_date;
    private String imagen_url;
    private Date termination_date;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="student")
    private Student student;
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="profesor")
    private Profesor profesor;

    public PersonaOutputDto personToPersonOutputDto() {
        return new PersonaOutputDto(
                this.persona,
                this.usuario,
                this.password,
                this.name,
                this.surname,
                this.company_email,
                this.personal_email,
                this.city,
                this.active,
                this.created_date,
                this.imagen_url,
                this.termination_date
        );
    }

    public PersonaProfesorOutputDto personToPersonProfesorOutputDto() {
        return new PersonaProfesorOutputDto(
                this.persona,
                this.usuario,
                this.name,
                this.surname,
                this.company_email,
                this.active,
                this.profesor.ProfesorToProfesorOutputDto()
        );
    }

    public Persona(PersonaInputDto personInputDto) {

        this.name = personInputDto.getName();
        this.surname= personInputDto.getSurname();
        this.company_email =personInputDto.getCompany_email();
        this.usuario=personInputDto.getUsuario();
        this.password=personInputDto.getPassword();
        this.personal_email=personInputDto.getPersonal_email();
        this.active=personInputDto.getActive();
        this.city=personInputDto.getCity();
        this.created_date=new Date(personInputDto.getCreated_date().getTime());
        this.imagen_url=personInputDto.getImagen_url();
        if(personInputDto.getTermination_date()!=null) {
            this.termination_date = new Date(personInputDto.getTermination_date().getTime());
        }
    }

    public PersonaStudentOutputDto personToPersonaEstudianteOutputDto() {
        return new PersonaStudentOutputDto(
                this.persona,
                this.usuario,
                this.name,
                this.surname,
                this.company_email,
                this.active,
                this.student.StudentToStudentOutputDto()
        );
    }
    

}
