package edu.upb.devops.estudiantes.service.interfaces;

import edu.upb.devops.estudiantes.model.Estudiante;

import java.util.List;

public interface IEstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> listarEstudiantes();
}
