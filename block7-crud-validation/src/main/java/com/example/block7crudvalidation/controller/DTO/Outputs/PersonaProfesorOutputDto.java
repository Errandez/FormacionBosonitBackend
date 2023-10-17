package com.example.block7crudvalidation.controller.DTO.Outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaProfesorOutputDto {
    private int id_persona;
    private String usuario;
    private String name;
    private String surname;
    private String company_email;
    private boolean active;
    private ProfesorOutputDto profesorOutputDto;
}
