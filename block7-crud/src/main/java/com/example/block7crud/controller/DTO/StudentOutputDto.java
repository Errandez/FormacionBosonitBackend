package com.example.block7crud.controller.DTO;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {
    private int id;
    private String name;
    private String lastName;
}
