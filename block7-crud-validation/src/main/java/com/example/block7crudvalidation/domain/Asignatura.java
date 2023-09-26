package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @GeneratedValue
    @GenericGenerator(name="codigoGenerador", strategy="com.example.block7crudvalidation.domain.CodigoGenerador")
    private String id_Asignatura;
    @ManyToMany(mappedBy = "id_student")
    private Set<Student> students;
    @Column(nullable = false)
    private Date initial_date;
    private Date finish_date;
}
