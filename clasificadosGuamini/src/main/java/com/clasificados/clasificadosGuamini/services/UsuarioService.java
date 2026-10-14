package com.clasificados.clasificadosGuamini.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDto mostrarUsuario(Long id) {
        Usuario usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        return mappearEntityADto(usuarioEntity);
    }

    public UsuarioResponseDto crearUsuario(UsuarioRequestDto dto) {

        // Valido si el nombre de usuario ya existe
        if (usuarioRepository.existsByNombreUsuario(dto.getNombreUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso.");
        }

        // Valido si el email ya existe
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }

        // Valido si el celular ya existe
        if (usuarioRepository.existsByCelular(dto.getCelular())) {
            throw new RuntimeException("El celular ya está registrado.");
        }

        //Encripto la contrasena con BCrypt
        String passwordEncriptada = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(passwordEncriptada);

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

    public boolean validarLogin(String nombreUsuario, String passwordIngresada) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario);    
        
        if (usuario == null) {
            return false; 
        }
        
        //Comparamos la contraseña ingresada con el hash guardado en la base de datos
        boolean passwordValida = passwordEncoder.matches(passwordIngresada, usuario.getPassword());
        return passwordValida;
    }

}
