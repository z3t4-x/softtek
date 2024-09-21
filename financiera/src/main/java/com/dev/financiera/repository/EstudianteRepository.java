package com.dev.financiera.repository;

import com.dev.financiera.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, Integer id);
}
