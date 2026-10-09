package com.espiritu.enfermeria.controller;

import com.espiritu.enfermeria.model.Valoracion;
import com.espiritu.enfermeria.service.ValoracionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/valoraciones")
public class ValoracionController {

    private final ValoracionService valoracionService;

    public ValoracionController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    // RF-ENF-07: Registrar valoración
    @PostMapping
    public ResponseEntity<Valoracion> registrar(@Valid @RequestBody Valoracion valoracion) {
        Valoracion nuevaValoracion = valoracionService.registrarValoracion(valoracion);
        return new ResponseEntity<>(nuevaValoracion, HttpStatus.CREATED);
    }

    // RF-ENF-09: Historial de valoraciones
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Valoracion>> obtenerHistorial(@PathVariable Long pacienteId) {
        List<Valoracion> historial = valoracionService.obtenerHistorialPorPaciente(pacienteId);
        return ResponseEntity.ok(historial);
    }
}