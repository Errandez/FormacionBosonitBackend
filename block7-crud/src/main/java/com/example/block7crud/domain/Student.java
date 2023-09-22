package com.example.block7crud.domain;


import com.example.block7crud.controller.DTO.StudentInputDto;
import com.example.block7crud.controller.DTO.StudentOutputDto;
import com.example.block7crud.repository.StudentRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String lastName;

    public Student (StudentInputDto studentInputDto){
        this.id = studentInputDto.getId();
        this.name = studentInputDto.getName();
        this.lastName = studentInputDto.getLastName();
    }

    public StudentOutputDto studentToStudentOutputDto(){
        return new StudentOutputDto(this.id,this.name,this.lastName);
    }
}
