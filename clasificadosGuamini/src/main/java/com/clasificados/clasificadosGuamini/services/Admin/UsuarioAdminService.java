package com.clasificados.clasificadosGuamini.services.Admin;

import java.util.List;
import org.springframework.stereotype.Service;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.AvisoMapper;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioAdminService {
    
    private final UsuarioRepository usuarioRepository;
    private final AvisoMapper avisoMapper;

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

    public List<AvisoResponseDto> AvisosPorUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + idUsuario));
        return usuario.getAvisos().stream().map(aviso -> avisoMapper.toResponseDto(aviso)).toList();
    }

    @Transactional
    public long cantidadUsuarios() {
        return usuarioRepository.count();
    }

}
