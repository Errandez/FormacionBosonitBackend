package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.application.ProfesorService;
import com.example.block7crudvalidation.controller.DTO.Inputs.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.Outputs.StudentOutputDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
//    @GeneratedValue
//    @GenericGenerator(name="codigoGenerador", strategy="com.example.block7crudvalidation.domain.CodigoGenerador")
    private int student;
    @OneToOne()
    @JoinColumn(name="persona")
    private Persona persona;
    @Column(nullable = false)
    private int num_hours_week;

    @ManyToOne()
    @JoinColumn(name="id_profesor",nullable = true, unique = true)
    @JsonBackReference
    private Profesor profesor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = true)
    private branchType branch;

    @ManyToMany
    @Column(nullable = true)

    private Set<Asignatura> asignaturas;


    public Student(StudentInputDto si){
        /*
        if(si.getId_student()!=null && !si.getId_student().isEmpty()) {
            this.id_persona = si.getId_persona();
        }
        if(si.getId_profesor()!=null) {
            this.id_profesor = si.getId_profesor();
        }
        if(si.getBranch()!=null) {
            this.branch = si.getBranch();
        }
        if(si.getNum_hours_week()!=null) {
            this.num_hours_week = si.getNum_hours_week();
        }
        */

    }



}
