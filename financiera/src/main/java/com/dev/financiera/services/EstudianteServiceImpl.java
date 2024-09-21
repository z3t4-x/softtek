package com.dev.financiera.services;

import com.dev.financiera.Utils.Constantes;
import com.dev.financiera.dto.EstudianteDTO;
import com.dev.financiera.dto.converters.EstudianteToDTO;
import com.dev.financiera.dto.converters.EstudianteToEntity;
import com.dev.financiera.entity.Estudiante;
import com.dev.financiera.exception.HttpException;
import com.dev.financiera.repository.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EstudianteServiceImpl implements EstudianteService {


    private final EstudianteRepository estudianteRepository;


    /**
     *
     */
    @Override
    public EstudianteDTO save(EstudianteDTO dto) {
        Estudiante estudiante = EstudianteToEntity.INSTANCE.apply(dto);
        Boolean existeCorreo = estudianteRepository.existsByEmail(estudiante.getEmail());
        if (Boolean.TRUE.equals(existeCorreo)) {
            throw new HttpException(HttpStatus.CONFLICT, Constantes.Error.ERROR_EMAIL_EXISTENTE);
        }
        return EstudianteToDTO.INSTANCE.apply(estudianteRepository.save(estudiante));

    }

    /**
     *
     */
    @Override
    public EstudianteDTO update(EstudianteDTO dto) {
        if (dto.getId() == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "El ID no puede ser nulo.");
        }
        
        Estudiante estudiante = EstudianteToEntity.INSTANCE.apply(dto);

        Boolean existeCorreo = estudianteRepository.existsByEmailAndIdNot(estudiante.getEmail(), estudiante.getId());
        if (Boolean.TRUE.equals(existeCorreo)) {
            throw new HttpException(HttpStatus.CONFLICT, Constantes.Error.ERROR_EMAIL_EXISTENTE);
        }

        return estudianteRepository.findById(estudiante.getId())
                .map(estudianteObj -> {
                    estudianteObj.setNombre(dto.getNombre());
                    estudianteObj.setApellido(dto.getApellido());
                    estudianteObj.setEmail(dto.getEmail());
                    estudianteObj.setSemestre(dto.getSemestre());
                    estudianteObj.setPromedio(dto.getPromedio());
                    Estudiante updatedEstudiante = estudianteRepository.save(estudianteObj);
                    return EstudianteToDTO.INSTANCE.apply(updatedEstudiante);
                }).orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, Constantes.Error.ERROR_ESTUDIANTE_NO_ENCONTRADO));
    }

    /**
     *
     */
    @Override
    public EstudianteDTO findById(Integer id) {
        return estudianteRepository.findById(id)
                .map(EstudianteToDTO.INSTANCE::apply)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, Constantes.Error.ERROR_ESTUDIANTE_NO_ENCONTRADO));
    }

    /**
     *
     */
    @Override
    public List<EstudianteDTO> findAll() {
        List<Estudiante> lstEstudiante = Optional.ofNullable(estudianteRepository.findAll())
                .orElse(Collections.emptyList());

        return lstEstudiante.stream()
                .map(EstudianteToDTO.INSTANCE::apply).toList();
    }

    /**
     *
     */
    @Override
    public void deleteById(Integer id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new HttpException(HttpStatus.NOT_FOUND, Constantes.Error.ERROR_ESTUDIANTE_NO_ENCONTRADO));
        estudianteRepository.delete(estudiante);
    }
}
