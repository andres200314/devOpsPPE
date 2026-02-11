package edu.upb.devops.estudiantes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Estudiante {
    private Long id;
    private String nombre;
    private String carrera;
}
