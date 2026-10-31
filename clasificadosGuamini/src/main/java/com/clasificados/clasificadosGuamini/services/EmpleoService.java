package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.mapStruct.EmpleoMapper;
import com.clasificados.clasificadosGuamini.repositories.EmpleoRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpleoService {

    private final EmpleoRepository empleoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmpleoMapper empleoMapper;
    private SecurityUtils securityUtils;

    public List<EmpleoResponseDto> listarEmpleos() {
        List<Empleo> listaEmpleos = empleoRepository.findAll();
        return listaEmpleos.stream().map(e -> empleoMapper.toDto(e)).toList();
    }

    public EmpleoResponseDto mostrarEmpleo(Long id) {
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public EmpleoResponseDto crearEmpleo(String nombreUsuarioActual, EmpleoRequestDto dto){
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Empleo empleo = empleoMapper.toEntity(dto, usuario);
        empleo.setAnunciante(usuario);
        Empleo empleoGuardado = empleoRepository.save(empleo);
        return empleoMapper.toDto(empleoGuardado);
    }

    @Transactional 
    public EmpleoResponseDto modificarEmpleo(String nombreUsuarioActual, Long id, EmpleoRequestDto dtoModificado) {
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoMapper.updateEntityFromDto(dtoModificado, empleo);
        /* Empleo empleoGuardado = empleoRepository.save(empleo); */
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public String eliminarEmpleo(String nombreUsuarioActual, Long id) {
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);        
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoRepository.delete(empleo);
        return "El empleo con id " + id + " se elimino correctamente";
    }

}
