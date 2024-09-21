package com.dev.financiera.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 40)
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 40)
    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(max = 40, message = "El apellido no puede tener más de 40 caracteres")
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 40)
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email debe ser válido")
    @Size(max = 40, message = "El email no puede tener más de 40 caracteres")
    private String email;

    @Column(name = "creditos", nullable = false)
    @NotNull(message = "Los créditos no pueden ser nulos")
    @Min(value = 0, message = "Los créditos no pueden ser negativos")
    private Integer creditos;


    @Column(name = "semestre", nullable = false)
    @NotNull(message = "El semestre no puede ser nulo")
    @Min(value = 1, message = "El semestre mínimo es 1")
    @Max(value = 12, message = "El semestre máximo es 12")
    private Integer semestre;

    @Column(name = "promedio", nullable = false)
    @NotNull(message = "El promedio no puede ser nulo")
    @Min(value = 0, message = "El promedio mínimo es 0")
    private Integer promedio;


}
