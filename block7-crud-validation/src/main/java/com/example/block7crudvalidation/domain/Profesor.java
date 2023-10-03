package com.example.block7crudvalidation.domain;

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
public class Profesor {
    @Id
    @UuidGenerator
    private String id_profesor;
    @OneToOne
    @JoinColumn(name = "Persona",nullable = false,unique = true)
    private Persona id_persona;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'FRONT'", nullable = false)
    private branchType branch;
    @OneToMany
    private Set<Student> Students;
}
