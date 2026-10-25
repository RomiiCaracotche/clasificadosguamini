package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import com.clasificados.clasificadosGuamini.repositories.InmuebleRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;
    private final UsuarioRepository usuarioRepository;
    private InmuebleMapper inmuebleMapper;

    public List<InmuebleResponseDto> listarInmuebles() {
        List<Inmueble> listaInmuebles = inmuebleRepository.findAll();
        return listaInmuebles.stream().map(e -> inmuebleMapper.toDto(e)).toList();
    }

    public InmuebleResponseDto mostrarInmueble(Long id) {
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public InmuebleResponseDto crearInmueble(InmuebleRequestDto inmuebleDto){
         Inmueble inmueble = inmuebleRepository.save(inmuebleMapper.toEntity(inmuebleDto));
         return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public InmuebleResponseDto modificarInmueble(Long id, InmuebleRequestDto dtoModificado) {
        Inmueble inmuebleExistente = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleMapper.updateEntityFromDto(dtoModificado, inmuebleExistente);
        Inmueble inmuebleGuardado = inmuebleRepository.save(inmuebleExistente);
        return inmuebleMapper.toDto(inmuebleGuardado);
    }

    @Transactional 
    public String eliminarInmueble(Long id) {
        Inmueble existeInmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleRepository.delete(existeInmueble);
        return "El inmueble con id " + id + " se elimino correctamente";
    }

}
