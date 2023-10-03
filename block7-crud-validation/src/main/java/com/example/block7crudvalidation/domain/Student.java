package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.DTO.StudentInputDto;
import com.example.block7crudvalidation.controller.DTO.StudentOutputDto;
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
    @UuidGenerator
    private String id_student;
    @OneToOne
    @JoinColumn(name="id_persona", nullable = false, unique = true)
    private Persona id_persona;
    @Column(nullable = false)
    private int num_hours_week;

    @OneToOne
    @JoinColumn(name="id_profesor",nullable = true, unique = true)
    private Profesor id_profesor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = true)
    private branchType branch;

    @ManyToMany(mappedBy = "students")
    @Column(nullable = true)
    private Set<Asignatura> asignaturas;


    public Student(StudentInputDto si){
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
    }

    public StudentOutputDto StudentToStudentOutputDto(){
        return new StudentOutputDto(this.id_student,
                this.id_persona,this.num_hours_week,this.id_profesor,this.branch,this.asignaturas);
    }

}
