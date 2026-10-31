package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.mapStruct.AvisoMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AvisoService {

    private final AvisoRepository avisoRepository;
    private final AvisoMapper avisoMapper; 

    public List<AvisoResponseDto> listarAprobados() {
        List<Aviso> avisos = avisoRepository.findByEstado(EstadoAviso.APROBADO);
        return avisoMapper.toResponseDtoList(avisos); 
    }

    public List<AvisoResponseDto> buscarPorPalabraClave(String palabraClave) {
        List<Aviso> avisos = avisoRepository.findByTituloContainingIgnoreCase(palabraClave);
        return avisoMapper.toResponseDtoList(avisos);
    }

}
    
