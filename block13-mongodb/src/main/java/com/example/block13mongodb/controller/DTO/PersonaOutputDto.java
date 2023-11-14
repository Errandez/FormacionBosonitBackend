package com.example.block13mongodb.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaOutputDto {
    private int id;
    private String name;
    private String poblacion;
    private int edad;
}
