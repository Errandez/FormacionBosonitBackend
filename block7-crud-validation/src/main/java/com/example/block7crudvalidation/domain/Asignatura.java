package com.example.block7crudvalidation.domain;

import com.example.block7crudvalidation.controller.DTO.Outputs.AsignaturaOutputDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    @GenericGenerator(name="codigoGenerador", strategy="com.example.block7crudvalidation.domain.CodigoGenerador")
    private int id_Asignatura;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="Student_Object",
            joinColumns={@JoinColumn(name="id_Asignatura")},
            inverseJoinColumns = {@JoinColumn(name="id_Student")}
    )
    private Set<Student> students;

    @Column(nullable = false)
    private Date initial_date;
    private Date finish_date;
    public AsignaturaOutputDto AsignaturaToAsignaturaOutputDto(){
        return new AsignaturaOutputDto(
                this.id_Asignatura,
                this.initial_date,
                this.finish_date
        );
    }
}
