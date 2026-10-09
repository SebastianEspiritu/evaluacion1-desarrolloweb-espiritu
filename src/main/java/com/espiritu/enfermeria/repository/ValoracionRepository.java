package com.espiritu.enfermeria.repository;

import com.espiritu.enfermeria.model.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Long> {

    // RF-ENF-09: Buscar por ID del Paciente ordenado por fecha
    List<Valoracion> findByPacienteIdOrderByFechaHoraDesc(Long pacienteId);
}