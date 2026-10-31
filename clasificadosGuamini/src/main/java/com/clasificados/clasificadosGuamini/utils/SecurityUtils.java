package com.clasificados.clasificadosGuamini.utils;

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


    public void verificarPropietarioOAdmin(String nombreUsuarioActual, Long id) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el id: " + id));
        Aviso aviso = avisoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        
        boolean esAnunciante = aviso.getAnunciante().getId().equals(usuario.getId());
        boolean esAdmin = usuario.getAdmin(); 

        if (!esAnunciante && !esAdmin) {
            throw new ForbiddenException("No tienes permiso para modificar este aviso");
        }
    }
}
