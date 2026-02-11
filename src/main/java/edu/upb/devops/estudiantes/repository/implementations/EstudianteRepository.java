package edu.upb.devops.estudiantes.repository.implementations;

import edu.upb.devops.estudiantes.model.Estudiante;
import edu.upb.devops.estudiantes.repository.interfaces.IEstudianteRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class EstudianteRepository implements IEstudianteRepository {

    private final Map<Long, Estudiante> estudiantes = new ConcurrentHashMap<>();

    @Override
    public Estudiante save(Estudiante estudiante) {
        if(findById(estudiante.getId()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un estudiante con el ID: " + estudiante.getId());
        }
        estudiantes.put(estudiante.getId(), estudiante);
        return estudiante;
    }

    @Override
    public List<Estudiante> findAll() {
        return new ArrayList<Estudiante>(estudiantes.values());
    }

    @Override
    public Optional<Estudiante> findById(Long id) {
        return Optional.ofNullable(estudiantes.get(id));
    }
}
