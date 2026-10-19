package com.clasificados.clasificadosGuamini.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Rol;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.dtos.DuplicateResourceException;
import com.clasificados.clasificadosGuamini.exception.dtos.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.UsuarioMapper;
import com.clasificados.clasificadosGuamini.repositories.RolRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;


@Service
@Transactional(readOnly = true)
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private PasswordEncoder passwordEncoder;
    private UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
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
        validarCamposExistentes(dto);
        Usuario usuario = usuarioMapper.toEntity(dto);
        
        Optional<Rol> rol = rolRepository.findByNombre("USUARIO");
        List<Rol> roles = new ArrayList<>();
        rol.ifPresent(roles::add);

        if(usuario.isAdmin()) {
            Optional<Rol> rolAdmin = rolRepository.findByNombre("ADMIN");
            rolAdmin.ifPresent(roles::add);
        }
        
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setRoles(roles);
        
        return usuarioMapper.toDto(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponseDto modificarUsuario(Long id, UsuarioRequestDto dtoModificado){
        Usuario usuarioExistente = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + id));
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


    private void validarCamposExistentes(UsuarioRequestDto dto) {
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

}

