package com.example.block7crudvalidation.domain;


import com.example.block7crudvalidation.application.UnprocessableEntityException;
import com.example.block7crudvalidation.controller.DTO.Inputs.PersonaInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
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
    @OneToOne()
    @JoinColumn(name="student")
    private Student student;

    @OneToOne()
    @JoinColumn(name="profesor")
    private Profesor profesor;

    public Persona (String usuario, String password, String name, String surname, String company_email, String personal_email
            , String city, Boolean active, String created_date, String imagen_url, String termination_date) throws Exception {
        try {
            if (usuario != null && !usuario.isEmpty()) {
                if (usuario.length() >= 6 && usuario.length() <= 10) {
                    this.usuario = usuario;
                } else {
                    throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres.");
                }
            } else {
                throw new UnprocessableEntityException("Usuario no puede ser nulo.");
            }
            if (password != null && !password.isEmpty()) {
                this.password = password;
            } else {
                throw new UnprocessableEntityException("Password no puede ser nulo.");
            }

            if (name != null && !name.isEmpty()) {
                this.name = name;
            } else {
                throw new UnprocessableEntityException("Name no puede ser nulo.");
            }

            this.surname = surname;

            if (company_email != null && !company_email.isEmpty()) {
                this.company_email = company_email;
            } else {
                throw new UnprocessableEntityException("Company_email no puede ser nulo.");
            }

            if (personal_email != null && !personal_email.isEmpty()) {
                this.personal_email = personal_email;
            } else {
                throw new UnprocessableEntityException("Personal_email no puede ser nulo.");
            }

            if (city != null && !city.isEmpty()) {
                this.city = city;
            } else {
                throw new UnprocessableEntityException("city no puede ser nulo.");
            }

            if (active != null) {
                this.active = active;
            } else {
                throw new UnprocessableEntityException("Active no puede ser nulo.");
            }

            if (created_date != null && !created_date.isEmpty()) {
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date d = formato.parse(created_date);
                this.created_date = new Date(d.getTime());
            } else {
                throw new UnprocessableEntityException("Created_date no puede ser nulo.");
            }

            this.imagen_url = imagen_url;

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = formato.parse(termination_date);
            this.created_date = new Date(d.getTime());
        }catch (UnprocessableEntityException e) {
            System.out.println("Date: " + e.getCustomError().getTimestamp());
            System.out.println("Http: " + e.getCustomError().getHttp());
            System.out.println("Mensaje: " + e.getCustomError().getMensaje());

        }
    }
    
    public Persona(PersonaInputDto persona) throws Exception {
        try {
            if (persona.getUsuario() != null && !persona.getUsuario().isEmpty()) {
                if (persona.getUsuario().length() >= 6 && persona.getUsuario().length() <= 10) {
                    this.usuario = persona.getUsuario();
                } else {
                    throw new UnprocessableEntityException("Longitud de usuario no puede ser superior a 10 caracteres.");
                }
            } else {
                throw new UnprocessableEntityException("Usuario no puede ser nulo.");
            }
            if (persona.getPassword() != null && !persona.getPassword().isEmpty()) {
                this.password = persona.getPassword();
            } else {
                throw new UnprocessableEntityException("Password no puede ser nulo.");
            }

            if (persona.getName() != null && !persona.getName().isEmpty()) {
                this.name = persona.getName();
            } else {
                throw new UnprocessableEntityException("Name no puede ser nulo.");
            }

            this.surname = persona.getSurname();

            if (persona.getCompany_email() != null && !persona.getCompany_email().isEmpty()) {
                this.company_email = persona.getCompany_email();
            } else {
                throw new UnprocessableEntityException("Company_email no puede ser nulo.");
            }

            if (persona.getPersonal_email() != null && !persona.getPersonal_email().isEmpty()) {
                this.personal_email = persona.getPersonal_email();
            } else {
                throw new UnprocessableEntityException("Personal_email no puede ser nulo.");
            }

            if (persona.getCity() != null && !persona.getCity().isEmpty()) {
                this.city = persona.getCity();
            } else {
                throw new UnprocessableEntityException("city no puede ser nulo.");
            }

            if (persona.getActive() != null) {
                this.active = persona.getActive();
            } else {
                throw new UnprocessableEntityException("Active no puede ser nulo.");
            }

            if (persona.getCreated_date() != null) {
                this.created_date = new Date(persona.getCreated_date().getTime());
            } else {
                throw new UnprocessableEntityException("Created_date no puede ser nulo.");
            }

            this.imagen_url = persona.getImagen_url();

            if (persona.getTermination_date() != null) {
                this.created_date = new Date(persona.getTermination_date().getTime());
            }
            if(persona.getId_student()!=null){
                this.student=persona.getId_student();
            }
        }catch (UnprocessableEntityException e) {
            System.out.println("Date: " + e.getCustomError().getTimestamp());
            System.out.println("Http: " + e.getCustomError().getHttp());
            System.out.println("Mensaje: " + e.getCustomError().getMensaje());
        }
    }

    public PersonaOutputDto PersonaToPersonaOutputDto(){
        return new PersonaOutputDto(
                this.getPersona(),this.getUsuario(),
                this.getPassword(),this.getName(),
                this.getSurname(), this.getCompany_email(),
                this.getPersonal_email(), this.getCity(),
                this.getActive(), this.getCreated_date(),
                this.getImagen_url(), this.getTermination_date());
    }
    

}
