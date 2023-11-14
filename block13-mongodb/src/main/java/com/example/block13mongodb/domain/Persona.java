package com.example.block13mongodb.domain;


import com.example.block13mongodb.controller.DTO.PersonaInputDto;
import com.example.block13mongodb.controller.DTO.PersonaOutputDto;
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
public class Persona {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String poblacion;
    private int edad;

    public Persona (PersonaInputDto personaInputDto){
        this.id = personaInputDto.getId();
        this.name = personaInputDto.getName();
        this.poblacion = personaInputDto.getPoblacion();
        this.edad = personaInputDto.getEdad();
    }

    public PersonaOutputDto PersonaToPersonaOutputDto(){
        return new PersonaOutputDto(this.id,this.name,this.poblacion,this.edad);
    }
}
