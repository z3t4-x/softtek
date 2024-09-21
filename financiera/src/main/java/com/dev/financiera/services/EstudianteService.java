package com.dev.financiera.services;

import com.dev.financiera.dto.EstudianteDTO;

import java.util.List;

public interface EstudianteService {

    /**
     * Guarda un nuevo estudiante.
     *
     * @param estudianteDTO el estudiante a guardar.
     * @return el estudiante guardado.
     */
    EstudianteDTO save(EstudianteDTO estudianteDTO);

    /**
     * Actualiza un estudiante existente.
     *
     * @param dto el estudiante con la información actualizada.
     * @return el estudiante actualizado.
     */
    EstudianteDTO update(EstudianteDTO dto);

    /**
     * Busca un estudiante por ID.
     *
     * @param id el ID del estudiante.
     * @return el estudiante encontrado.
     */
    EstudianteDTO findById(Integer id);

    /**
     * Obtiene todos los estudiantes.
     *
     * @return la lista de estudiantes.
     */
    List<EstudianteDTO> findAll();

    /**
     * Elimina un estudiante por ID.
     *
     * @param id el ID del estudiante a eliminar.
     */
    void deleteById(Integer id);
}
