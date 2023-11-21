package com.example.block7crudvalidation.controller.DTO.Outputs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaProfesorOutputDto extends PersonaOutputDto {
    private ProfesorOutputDto profesorOutputDto;
}
