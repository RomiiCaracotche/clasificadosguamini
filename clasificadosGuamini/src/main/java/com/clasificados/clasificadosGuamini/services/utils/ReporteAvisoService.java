package com.clasificados.clasificadosGuamini.services.utils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.request.ReporteAvisoRequestDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.ReporteAviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.ReporteAvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data 
public class ReporteAvisoService {

    private final AvisoRepository avisoRepository;
    private final ReporteAvisoRepository ReporteAvisoRepository;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void reportarAviso(Long id, ReporteAvisoRequestDto dto, String nombreUsuario) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Aviso aviso =  avisoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aviso no encontrado"));

        if (aviso.getAnunciante().getId().equals(usuario.getId())) {
            throw new RuntimeException("No puedes reportar tu propia publicación");
        }

        ReporteAviso reporte = new ReporteAviso();
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setAviso(aviso);
        reporte.setUsuario(usuario);

        ReporteAvisoRepository.save(reporte);
    }

}
