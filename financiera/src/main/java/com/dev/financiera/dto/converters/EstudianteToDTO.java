package com.dev.financiera.dto.converters;

import com.dev.financiera.dto.EstudianteDTO;
import com.dev.financiera.entity.Estudiante;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum EstudianteToDTO implements Function<Estudiante, EstudianteDTO> {
    INSTANCE;

    @Override
    public EstudianteDTO apply(Estudiante entity) {
        EstudianteDTO dto = new EstudianteDTO();

        if (entity != null) {
            BeanUtils.copyProperties(entity, dto);
        }
        return dto;
    }
}
