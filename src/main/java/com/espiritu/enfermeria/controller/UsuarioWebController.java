package com.espiritu.enfermeria.controller;

import com.espiritu.enfermeria.model.Usuario;
import com.espiritu.enfermeria.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/usuarios")
public class UsuarioWebController {

    private final UsuarioService usuarioService;

    public UsuarioWebController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "lista-usuarios";
    }

    @GetMapping("/nuevo")
    public String nuevoUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rolesList", usuarioService.listarRoles());
        return "form-usuario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
        return "redirect:/web/usuarios";
    }

    @GetMapping("/estado/{id}")
    public String cambiarEstado(@PathVariable Long id) {
        usuarioService.cambiarEstado(id);
        return "redirect:/web/usuarios";
    }
}