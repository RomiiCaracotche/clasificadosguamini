package com.clasificados.clasificadosGuamini.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ForbiddenException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@AllArgsConstructor 
@Data 
public class SecurityUtils {

    private final UsuarioRepository usuarioRepository;
    private final AvisoRepository avisoRepository;

    public Usuario obtenerUsuarioActual() {
        String idStr = SecurityContextHolder.getContext().getAuthentication().getName();
        Long idUsuario = Long.parseLong(idStr);   
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario"));
        return usuario;
    }

    public void verificarPropietarioOAdmin(Long idUsuarioActual, Long id) {
        Usuario usuario = usuarioRepository.findById(idUsuarioActual).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el id: " + id));
        Aviso aviso = avisoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        
        boolean esAnunciante = aviso.getAnunciante().getId().equals(usuario.getId());
        boolean esAdmin = usuario.getAdmin(); 

        if (!esAnunciante && !esAdmin) {
            throw new ForbiddenException("No tienes permiso para modificar este aviso");
        }
    }
}
