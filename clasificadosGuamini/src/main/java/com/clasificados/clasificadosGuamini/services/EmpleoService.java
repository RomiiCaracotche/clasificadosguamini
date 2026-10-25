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
import lombok.RequiredArgsConstructor;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpleoService {

    private final EmpleoRepository empleoRepository;
    private final UsuarioRepository usuarioRepository;
    private EmpleoMapper empleoMapper;

    public List<EmpleoResponseDto> listarEmpleos() {
        List<Empleo> listaEmpleos = empleoRepository.findAll();
        return listaEmpleos.stream().map(e -> empleoMapper.toDto(e)).toList();
    }

    public EmpleoResponseDto mostrarEmpleo(Long id) {
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public EmpleoResponseDto crearEmpleo(EmpleoRequestDto dto){
        Empleo empleo = empleoRepository.save(empleoMapper.toEntity(Empleodto));
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public EmpleoResponseDto modificarEmpleo(Long id, EmpleoRequestDto dtoModificado) {
        Empleo empleoExistente = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoMapper.updateEntityFromDto(dtoModificado, empleoExistente);
        Empleo empleoGuardado = empleoRepository.save(empleoExistente);
        return empleoMapper.toDto(empleoGuardado);
    }

    @Transactional 
    public String eliminarEmpleo(Long id) {
        Empleo existeEmpleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoRepository.delete(existeEmpleo);
        return "El empleo con id " + id + " se elimino correctamente";
    }

}
