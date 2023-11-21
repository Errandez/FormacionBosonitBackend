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
public class PersonaStudentOutputDto extends PersonaOutputDto {
    private StudentOutputDto studentOutputDto;

}
