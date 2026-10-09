package com.espiritu.enfermeria.service;

import com.espiritu.enfermeria.model.Auditoria;
import com.espiritu.enfermeria.model.Valoracion;
import com.espiritu.enfermeria.repository.AuditoriaRepository;
import com.espiritu.enfermeria.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    private final ValoracionRepository valoracionRepository;
    private final AuditoriaRepository auditoriaRepository;

    public ValoracionServiceImpl(ValoracionRepository valoracionRepository, AuditoriaRepository auditoriaRepository) {
        this.valoracionRepository = valoracionRepository;
        this.auditoriaRepository = auditoriaRepository;
    }

    @Override
    @Transactional
    public Valoracion registrarValoracion(Valoracion valoracion) {
        Valoracion nueva = valoracionRepository.save(valoracion);

        // Registro de Auditoría
        Auditoria audit = Auditoria.builder()
                .usuario("SISTEMA_ENFERMERIA")
                .fechaHora(LocalDateTime.now())
                .operacion("REGISTRO")
                .entidadAfectada("Valoracion")
                .registroId(nueva.getId())
                .build();
        auditoriaRepository.save(audit);

        return nueva;
    }

    @Override
    public List<Valoracion> obtenerHistorialPorPaciente(Long pacienteId) {
        return valoracionRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);
    }
}