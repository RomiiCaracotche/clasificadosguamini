package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import com.clasificados.clasificadosGuamini.repositories.InmuebleRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;
    private final UsuarioRepository usuarioRepository;
    private final InmuebleMapper inmuebleMapper;
    private SecurityUtils securityUtils;

    public List<InmuebleResponseDto> listarInmuebles() {
        List<Inmueble> listaInmuebles = inmuebleRepository.findAll();
        return listaInmuebles.stream().map(e -> inmuebleMapper.toDto(e)).toList();
    }

    public InmuebleResponseDto mostrarInmueble(Long id) {
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public InmuebleResponseDto crearInmueble(String nombreUsuarioActual, InmuebleRequestDto dto){
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Inmueble inmueble = inmuebleMapper.toEntity(dto, usuario);
        inmueble.setAnunciante(usuario);
        Inmueble inmuebleGuardado = inmuebleRepository.save(inmueble);
        return inmuebleMapper.toDto(inmuebleGuardado);
    }

    @Transactional 
    public InmuebleResponseDto modificarInmueble(String nombreUsuarioActual, Long id, InmuebleRequestDto dtoModificado) {
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleMapper.updateEntityFromDto(dtoModificado, inmueble);
        /* Inmueble inmuebleGuardado = inmuebleRepository.save(inmueble); */
        return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public String eliminarInmueble(String nombreUsuarioActual, Long id) {
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleRepository.delete(inmueble);
        return "El inmueble con id " + id + " se elimino correctamente";
    }

}
