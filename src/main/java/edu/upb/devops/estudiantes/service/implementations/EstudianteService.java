package edu.upb.devops.estudiantes.service.implementations;

import edu.upb.devops.estudiantes.model.Estudiante;
import edu.upb.devops.estudiantes.repository.interfaces.IEstudianteRepository;
import edu.upb.devops.estudiantes.service.interfaces.IEstudianteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    private final IEstudianteRepository estudianteRepository;

    public EstudianteService(IEstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }
    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser null");
        }
        if (estudiante.getId() == null) {
            throw new IllegalArgumentException("El ID del estudiante es obligatorio");
        }
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio");
        }
        if (estudiante.getCarrera() == null || estudiante.getCarrera().trim().isEmpty()) {
            throw new IllegalArgumentException("La carrera del estudiante es obligatoria");
        }

        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }
}
