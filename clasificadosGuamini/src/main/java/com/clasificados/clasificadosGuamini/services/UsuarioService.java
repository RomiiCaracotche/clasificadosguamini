package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.personalizadas.BadRequestException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.DuplicateResourceException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.AvisoMapper;
import com.clasificados.clasificadosGuamini.mapStruct.UsuarioMapper;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AvisoRepository avisoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final AvisoMapper avisoMapper;

    public List<UsuarioResponseDto> listarUsuarios() {
        List<Usuario> Usuarios = usuarioRepository.findAll();
        return Usuarios.stream().map(e -> usuarioMapper.toDto(e)).toList();
    }
    
    public UsuarioResponseDto mostrarUsuario(Long id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el id: " + id));
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
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el Usuario con id: " + id));
        validarCamposUnicosAlModificar(dtoModificado, usuario);
        usuarioMapper.updateEntityFromDto(dtoModificado, usuario);
        /* Usuario usuarioGuardado = usuarioRepository.save(usuarioExistente); */
        return usuarioMapper.toDto(usuario);
    }

    @Transactional
    public String eliminarUsuario(Long id){
        Usuario existeId = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario con id: " + id));
        usuarioRepository.delete(existeId);
        return "El usuario con id " + id + " se elimino correctamente";
    }


    //FAVORITOS

    @Transactional
    public void agregarFavorito(String nombreUsuarioActual, Long idAviso) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario"));
        Aviso aviso = avisoRepository.findById(idAviso).orElseThrow(() -> new ResourceNotFoundException("No se encontro el aviso con id: " + idAviso));
        if(usuario.getId().equals(aviso.getAnunciante().getId())) {
            throw new BadRequestException("No podes agregar a favoritos un aviso tuyo");
        }
        usuario.agregarFavorito(aviso);
        /* usuarioRepository.save(usuario); */
    }

    @Transactional
    public void eliminarFavorito(String nombreUsuarioActual, Long idAviso) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario"));
        Aviso aviso = avisoRepository.findById(idAviso).orElseThrow(() -> new ResourceNotFoundException("No se encontro el aviso con id: " + idAviso));
        usuario.quitarFavorito(aviso);
        /* usuarioRepository.save(usuario); */
    }

    @Transactional(readOnly = true)
    public Set<AvisoResponseDto> obtenerFavoritos(String nombreUsuarioActual) {
        Usuario usuario = usuarioRepository.findByNombreUsuarioWithFavoritos(nombreUsuarioActual).orElseThrow(() -> new ResourceNotFoundException("No se encontro el usuario"));
        return usuario.getFavoritos().stream().map(aviso -> avisoMapper.toResponseDto(aviso)).collect(Collectors.toSet());
    }


    // METODOS PRIVADOS

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

