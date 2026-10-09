package com.espiritu.enfermeria.controller;

import com.espiritu.enfermeria.model.Valoracion;
import com.espiritu.enfermeria.service.ValoracionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/valoraciones")
public class ValoracionWebController {

    private final ValoracionService valoracionService;

    public ValoracionWebController(ValoracionService valoracionService) {
        this.valoracionService = valoracionService;
    }

    // RF-ENF-07: Formulario para registrar valoración
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("valoracion", new Valoracion());
        return "form-valoracion";
    }

    // Procesar el formulario de registro
    @PostMapping("/guardar")
    public String guardarValoracion(@ModelAttribute("valoracion") Valoracion valoracion) {
        valoracionService.registrarValoracion(valoracion);
        return "redirect:/web/valoraciones/historial?pacienteId=" + valoracion.getPacienteId();
    }

    // RF-ENF-09: Vista para buscar e imprimir el historial de un paciente
    @GetMapping("/historial")
    public String verHistorial(@RequestParam(name = "pacienteId", required = false) Long pacienteId, Model model) {
        if (pacienteId != null) {
            List<Valoracion> historial = valoracionService.obtenerHistorialPorPaciente(pacienteId);
            model.addAttribute("historial", historial);
            model.addAttribute("pacienteIdBuscado", pacienteId);
        }
        return "historial-valoracion";
    }
}