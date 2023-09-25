package com.example.block7crud.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaInputDto {
    private int id;
    private String name;
    private String poblacion;
    private int edad;
}
