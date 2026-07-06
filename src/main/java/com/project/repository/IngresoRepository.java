package com.project.repository;

import com.project.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    List<Ingreso> findByUsuarioId(Long usuarioId);
}
