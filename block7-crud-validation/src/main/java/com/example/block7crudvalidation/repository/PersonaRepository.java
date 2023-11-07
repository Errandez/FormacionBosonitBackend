package com.example.block7crudvalidation.repository;

import com.example.block7crudvalidation.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>, PagingAndSortingRepository<Persona,Integer> {
    @Query("SELECT p FROM Persona p WHERE p.name=?1")
    List<Persona> getPersonaByName(String Name);

}
