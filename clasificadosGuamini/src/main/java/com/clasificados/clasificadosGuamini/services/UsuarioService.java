package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.dtos.DuplicateResourceException;
import com.clasificados.clasificadosGuamini.exception.dtos.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.UsuarioMapper;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioResponseDto> listarUsuarios() {
        List<Usuario> Usuarios = usuarioRepository.findAll();
        return Usuarios.stream().map(e -> usuarioMapper.toDto(e)).toList();
    }
    
    public UsuarioResponseDto mostrarUsuario(Long id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        return usuarioMapper.toDto(usuarioEntity);
    }

    @Transactional
    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {
        validarCamposUnicosAlCrear(dto);
        Usuario usuario = usuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword())); 
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponseDto modificarUsuario(Long id, UsuarioRequestDto dtoModificado){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + id));
        validarCamposUnicosAlModificar(dtoModificado, usuarioExistente);
        usuarioMapper.updateEntityFromDto(dtoModificado, usuarioExistente);
        Usuario usuarioGuardado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toDto(usuarioGuardado);
    }

    @Transactional
    public String eliminarUsuario(Long id){
        Usuario existeId = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + id));
        usuarioRepository.delete(existeId);
        return "El usuario con id " + id + " se elimino correctamente";
    }


    private void validarCamposUnicosAlCrear(UsuarioRequestDto dto) {
        if (usuarioRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("El número de documento ya está registrado");
        }
        if (usuarioRepository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new DuplicateResourceException("El nombre de usuario ya está registrado");
        }
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("El email ya está registrado");
        }
        if (usuarioRepository.existsByCelular(dto.getCelular())) {
            throw new DuplicateResourceException("El número de celular ya está registrado");
        }
    }

    private void validarCamposUnicosAlModificar(UsuarioRequestDto dtoModificado, Usuario usuarioExistente) {

         if (!dtoModificado.getDni().equals(usuarioExistente.getDni())) {
            if (usuarioRepository.existsByDni(dtoModificado.getDni())) {
                throw new IllegalArgumentException("El dni ya esta registrado por otro usuario");
            }
        }
        if (!dtoModificado.getCelular().equals(usuarioExistente.getCelular())) {
            if (usuarioRepository.existsByCelular(dtoModificado.getCelular())) {
                throw new IllegalArgumentException("El celular ya esta registrado por otro usuario");
            }
        }
        if (!dtoModificado.getNombreUsuario().equals(usuarioExistente.getNombreUsuario())) {
            if (usuarioRepository.existsByNombreUsuario(dtoModificado.getNombreUsuario())) {
                throw new IllegalArgumentException("El nombre de usuario ya esta en uso");
            }
        }
        if (!dtoModificado.getEmail().equals(usuarioExistente.getEmail())) {
            if (usuarioRepository.existsByEmail(dtoModificado.getEmail())) {
                throw new IllegalArgumentException("El email ya esta registrado");
            }
        }
    }

}

