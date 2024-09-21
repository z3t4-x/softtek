package com.dev.financiera.controllers;


import com.dev.financiera.dto.EstudianteDTO;
import com.dev.financiera.services.EstudianteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    /**
     * Guarda un nuevo estudiante.
     *
     * @param estudianteDTO el estudiante a guardar.
     * @return ResponseEntity con el estudiante guardado y el código de estado.
     */
    @PostMapping
    public ResponseEntity<EstudianteDTO> save(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        EstudianteDTO savedEstudiante = estudianteService.save(estudianteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEstudiante);
    }

    /**
     * Actualiza un estudiante existente.
     *
     * @param estudianteDTO el estudiante con la información actualizada.
     * @return ResponseEntity con el estudiante actualizado.
     */
    @PutMapping
    public ResponseEntity<EstudianteDTO> update(@Valid @RequestBody EstudianteDTO estudianteDTO) {

        EstudianteDTO updatedEstudiante = estudianteService.update(estudianteDTO);
        return ResponseEntity.ok(updatedEstudiante);
    }

    /**
     * Busca un estudiante por ID.
     *
     * @param id el ID del estudiante.
     * @return ResponseEntity con el estudiante encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> findById(@PathVariable Integer id) {
        EstudianteDTO estudiante = estudianteService.findById(id);
        return ResponseEntity.ok(estudiante);
    }

    /**
     * Obtiene todos los estudiantes.
     *
     * @return ResponseEntity con la lista de estudiantes.
     */
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> findAll() {
        List<EstudianteDTO> estudiantes = estudianteService.findAll();
        return ResponseEntity.ok(estudiantes);
    }

    /**
     * Elimina un estudiante por ID.
     *
     * @param id el ID del estudiante a eliminar.
     * @return ResponseEntity con el código de estado No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
