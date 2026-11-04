package com.clasificados.clasificadosGuamini.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ForbiddenException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor; 

@Component
@RequiredArgsConstructor 
public class SecurityUtils {

    private final UsuarioRepository usuarioRepository;
    private final AvisoRepository avisoRepository;

    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioActual() {
        String idStr = SecurityContextHolder.getContext().getAuthentication().getName();
        Long idUsuario = Long.parseLong(idStr);    
        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario en el sistema"));
    }

    @Transactional(readOnly = true)
    public void verificarPropietarioOAdmin(Long idAviso) {
        Usuario usuarioActual = obtenerUsuarioActual();
        Aviso aviso = avisoRepository.findById(idAviso).orElseThrow(() -> new ResourceNotFoundException("No se encontró el aviso con id: " + idAviso));
        
        boolean esAnunciante = aviso.getAnunciante().getId().equals(usuarioActual.getId());
        boolean esAdmin = usuarioActual.getAdmin(); 

        if (!esAnunciante && !esAdmin) {
            throw new ForbiddenException("No tienes permiso para modificar este aviso");
        }
    }
}
