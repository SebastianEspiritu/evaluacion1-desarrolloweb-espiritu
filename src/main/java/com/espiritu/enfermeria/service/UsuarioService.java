package com.espiritu.enfermeria.service;

import com.espiritu.enfermeria.model.Rol;
import com.espiritu.enfermeria.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardarUsuario(Usuario usuario);
    Usuario obtenerPorId(Long id);
    void cambiarEstado(Long id);
    List<Rol> listarRoles();
}