package com.example.block11uploaddownloadfiles.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FicheroOutputDto {
    private int id;
    private String nombre;
    private Date fechaSubida;
    private String categoria;
}
