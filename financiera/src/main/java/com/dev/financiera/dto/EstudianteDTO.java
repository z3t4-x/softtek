package com.dev.financiera.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private Integer creditos;
    private Integer semestre;
    private Integer promedio;

}
