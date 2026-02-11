package edu.upb.devops.estudiantes.repository.interfaces;

import edu.upb.devops.estudiantes.model.Estudiante;

import java.util.List;
import java.util.Optional;


public interface IEstudianteRepository {

    Estudiante save(Estudiante estudiante);
    List<Estudiante> findAll();
    Optional<Estudiante> findById(Long id);

}
