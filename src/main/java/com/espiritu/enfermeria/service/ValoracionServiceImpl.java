package com.espiritu.enfermeria.service;

import com.espiritu.enfermeria.model.Valoracion;
import com.espiritu.enfermeria.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionService {

    private final ValoracionRepository valoracionRepository;

    public ValoracionServiceImpl(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    @Override
    public Valoracion registrarValoracion(Valoracion valoracion) {
        return valoracionRepository.save(valoracion);
    }

    @Override
    public List<Valoracion> obtenerHistorialPorPaciente(Long pacienteId) {
        return valoracionRepository.findByPacienteIdOrderByFechaHoraDesc(pacienteId);
    }
}