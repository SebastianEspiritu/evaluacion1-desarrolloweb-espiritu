package com.espiritu.enfermeria.service;

import com.espiritu.enfermeria.model.Rol;
import com.espiritu.enfermeria.model.Usuario;
import com.espiritu.enfermeria.repository.RolRepository;
import com.espiritu.enfermeria.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void cambiarEstado(Long id) {
        Usuario user = obtenerPorId(id);
        if (user != null) {
            user.setActivo(!user.isActivo());
            usuarioRepository.save(user);
        }
    }

    @Override
    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}