package com.clasificados.clasificadosGuamini.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponseDto mostrarUsuario(Long id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        return mappearEntityADto(usuarioEntity);
    }

    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {
        Usuario usuarioEntity = usuarioRepository.save(mappearDtoAEntity(dto, null));
        return mappearEntityADto(usuarioEntity);
    }

    private Usuario mappearDtoAEntity(UsuarioRequestDto dto, Usuario entidadExiste) {
        if(entidadExiste == null) {
            entidadExiste = new Usuario();
        }
        
        entidadExiste.setNombre(dto.getNombre());
        entidadExiste.setApellido(dto.getApellido());
        entidadExiste.setNombreUsuario(dto.getNombreUsuario());
        entidadExiste.setPassword(dto.getPassword());
        entidadExiste.setEmail(dto.getEmail());
        entidadExiste.setCelular(dto.getCelular());
        entidadExiste.setLocalidad(dto.getLocalidad());

        return entidadExiste;
    }

    private UsuarioResponseDto mappearEntityADto(Usuario entidad) {
        UsuarioResponseDto dto = new UsuarioResponseDto();

        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setApellido(entidad.getApellido());
        dto.setNombreUsuario(entidad.getNombreUsuario());
        dto.setEmail(entidad.getEmail());
        dto.setCelular(entidad.getCelular());
        dto.setLocalidad(entidad.getLocalidad());
        dto.setFechaRegistro(entidad.getFechaRegistro());
        dto.setActivo(entidad.getActivo());

        return dto;
    }

}
