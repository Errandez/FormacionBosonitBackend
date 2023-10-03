package com.example.block7crudvalidation.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asignatura {
    @Id
    @UuidGenerator
    private String id_Asignatura;

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
}
