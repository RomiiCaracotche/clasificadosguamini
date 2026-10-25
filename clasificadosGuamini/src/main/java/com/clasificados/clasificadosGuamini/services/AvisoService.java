package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import com.clasificados.clasificadosGuamini.mapStruct.AvisoMapper;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.mapStruct.EmpleoMapper;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvisoService {

    private final AvisoRepository avisoRepository;
    private final AvisoMapper avisoMapper; 

    public List<AvisoResponseDto> listarTodos() {
        List<Aviso> avisos = avisoRepository.findAll();
        return avisoMapper.toResponseDtoList(avisos); 
    }

    public List<AvisoResponseDto> buscarPorPalabraClave(String palabraClave) {
        List<Aviso> avisos = avisoRepository.findByTituloContainingIgnoreCase(palabraClave);
        return avisoMapper.toResponseDtoList(avisos);
    }

}
    
