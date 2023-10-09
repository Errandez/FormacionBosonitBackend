package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.StudentOutputDto;
import com.example.block7crudvalidation.repository.PersonaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;

@Entity
@Data
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

    @OneToOne
    @JoinColumn(name="id_profesor",nullable = true, unique = true)
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

    public StudentOutputDto StudentToStudentOutputDto(){
        return new StudentOutputDto(this.student,
                this.persona,this.num_hours_week,this.profesor,this.branch,this.asignaturas);
    }

}
