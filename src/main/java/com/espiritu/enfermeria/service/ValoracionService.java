package com.espiritu.enfermeria.service;

import com.espiritu.enfermeria.model.Valoracion;
import java.util.List;

public interface ValoracionService {
    Valoracion registrarValoracion(Valoracion valoracion);
    List<Valoracion> obtenerHistorialPorPaciente(Long pacienteId);
}