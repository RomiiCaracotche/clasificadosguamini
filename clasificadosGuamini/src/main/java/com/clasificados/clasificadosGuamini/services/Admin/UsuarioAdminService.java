package com.clasificados.clasificadosGuamini.services.Admin;

import org.springframework.stereotype.Service;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.dtos.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioAdminService {
    
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void cambiarRolUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + idUsuario));
        usuario.setAdmin(!usuario.getAdmin());
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void deshabilitarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + idUsuario));
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public void habilitarUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + idUsuario));
        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    @Transactional
    public long cantidadUsuarios() {
        return usuarioRepository.count();
    }

}
