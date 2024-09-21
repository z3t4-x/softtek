package com.dev.financiera.services;


import com.dev.financiera.dto.EstudianteDTO;
import com.dev.financiera.entity.Estudiante;
import com.dev.financiera.exception.HttpException;
import com.dev.financiera.repository.EstudianteRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteServiceImpl estudianteService;


    @Before
    void setUp() {
    }

    @Test
    public void testSave_Success() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setNombre("Roy Roque");
        dto.setEmail("martinez@email.com");
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Roy Roque");
        estudiante.setEmail("martinez@email.com");

        when(estudianteRepository.existsByEmail("martinez@email.com")).thenReturn(false);
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(estudiante);

        EstudianteDTO result = estudianteService.save(dto);

        assertNotNull(result);
        assertEquals("Roy Roque", result.getNombre());
        assertEquals("martinez@email.com", result.getEmail());
        verify(estudianteRepository).save(any(Estudiante.class));
    }

    @Test
    public void testSave_EmailExists() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setNombre("Roy Roque");
        dto.setEmail("martinez@email.com");

        when(estudianteRepository.existsByEmail("martinez@email.com")).thenReturn(true);
        assertThrows(HttpException.class, () -> estudianteService.save(dto));
    }

    @Test
    public void testUpdate_Success() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(1);
        dto.setNombre("Roy Roque");
        dto.setEmail("martinez@email.com");

        Estudiante existingEstudiante = new Estudiante();
        existingEstudiante.setId(1);

        when(estudianteRepository.existsByEmailAndIdNot("martinez@email.com", 1)).thenReturn(false);
        when(estudianteRepository.findById(1)).thenReturn(Optional.of(existingEstudiante));
        when(estudianteRepository.save(any(Estudiante.class))).thenReturn(existingEstudiante);

        EstudianteDTO result = estudianteService.update(dto);

        assertNotNull(result);
        assertEquals("Roy Roque", existingEstudiante.getNombre());
        assertEquals("martinez@email.com", existingEstudiante.getEmail());
        verify(estudianteRepository).save(existingEstudiante);
    }

    @Test
    public void testUpdate_EmailExists() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(1);
        dto.setNombre("Roy Roque");
        dto.setEmail("martinez@email.com");

        when(estudianteRepository.existsByEmailAndIdNot("martinez@email.com", 1)).thenReturn(true);
        assertThrows(HttpException.class, () -> estudianteService.update(dto));

    }

    @Test
    public void testUpdate_StudentNotFound() {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(1);
        dto.setNombre("Roy Roque");
        dto.setEmail("martinez@email.com");

        when(estudianteRepository.existsByEmailAndIdNot("martinez@email.com", 1)).thenReturn(false);
        when(estudianteRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(HttpException.class, () -> estudianteService.update(dto));

    }

    @Test
    public void testFindById_Success() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombre("Roy Roque");
        estudiante.setEmail("martinez@email.com");

        when(estudianteRepository.findById(1)).thenReturn(Optional.of(estudiante));

        EstudianteDTO result = estudianteService.findById(1);

        assertNotNull(result);
        assertEquals("Roy Roque", result.getNombre());
        assertEquals("martinez@email.com", result.getEmail());
    }

    @Test
    public void testFindById_NotFound() {
        when(estudianteRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(HttpException.class, () -> estudianteService.findById(1));

    }

    @Test
    public void testFindAll() {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setId(1);
        estudiante1.setNombre("Roy Roque");
        estudiante1.setEmail("martinez@email.com");

        Estudiante estudiante2 = new Estudiante();
        estudiante2.setId(2);
        estudiante2.setNombre("Otro Estudiante");
        estudiante2.setEmail("otro@email.com");

        when(estudianteRepository.findAll()).thenReturn(Arrays.asList(estudiante1, estudiante2));

        List<EstudianteDTO> result = estudianteService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Roy Roque", result.get(0).getNombre());
        assertEquals("martinez@email.com", result.get(0).getEmail());
    }

    @Test
    public void testFindAll_EmptyList() {
        when(estudianteRepository.findAll()).thenReturn(Collections.emptyList());

        List<EstudianteDTO> result = estudianteService.findAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDeleteById_Success() {
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombre("Roy Roque");
        estudiante.setEmail("martinez@email.com");

        when(estudianteRepository.findById(1)).thenReturn(Optional.of(estudiante));

        estudianteService.deleteById(1);

        verify(estudianteRepository).delete(estudiante);
    }

    @Test
    public void testDeleteById_NotFound() {
        when(estudianteRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(HttpException.class, () -> estudianteService.deleteById(1));
    }
}
