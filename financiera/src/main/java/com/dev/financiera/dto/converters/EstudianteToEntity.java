package com.dev.financiera.dto.converters;

import com.dev.financiera.dto.EstudianteDTO;
import com.dev.financiera.entity.Estudiante;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public enum EstudianteToEntity implements Function<EstudianteDTO, Estudiante> {

    INSTANCE;

    @Override
    public Estudiante apply(EstudianteDTO dto) {
        Estudiante entity = new Estudiante();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity);
        }

        return entity;
    }
}
