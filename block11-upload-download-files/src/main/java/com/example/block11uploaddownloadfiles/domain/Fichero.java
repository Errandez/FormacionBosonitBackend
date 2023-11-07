package com.example.block11uploaddownloadfiles.domain;

import com.example.block11uploaddownloadfiles.domain.DTO.FicheroOutputDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fichero {
    @Id
    @GeneratedValue
    private int id;
    @Column(nullable=false)
    private String nombre;
    @Column(nullable = false)
    private Date fechaSubida;
    @Column(nullable = false)
    private String categoria;

    public FicheroOutputDto FicheroToFicheroOutputDto(){
        return new FicheroOutputDto(this.getId(),this.getNombre(),this.getFechaSubida(),this.categoria);
    }

}
