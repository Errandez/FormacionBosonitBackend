package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.DTO.Outputs.PersonaOutputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.ProfesorOutputDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Table(name="Profesor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue
    private int id_profesor;
    @OneToOne
    @JoinColumn(name = "Persona",nullable = false,unique = true)
    private Persona persona;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = false)
    private branchType branch;
    @OneToMany(mappedBy = "profesor", cascade = CascadeType.REMOVE, orphanRemoval = true)

    private Set<Student> Students;
    public ProfesorOutputDto ProfesorToProfesorOutputDto(){
        PersonaOutputDto persona = null;
        if(this.getPersona()!=null){
            persona = this.getPersona().personToPersonOutputDto();
        }
        return new ProfesorOutputDto(this.getId_profesor(),
                persona,
                this.getBranch(),this.getStudents());
    }
}
