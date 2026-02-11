package edu.upb.devops.estudiantes.controller;

import edu.upb.devops.estudiantes.model.Estudiante;
import edu.upb.devops.estudiantes.service.interfaces.IEstudianteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstudianteController {
    private final IEstudianteService estudianteService;

    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/estudiantes")
    public ResponseEntity<?> crearEstudiante(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevo = estudianteService.crearEstudiante(estudiante);
            return ResponseEntity.status(201).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }

}
