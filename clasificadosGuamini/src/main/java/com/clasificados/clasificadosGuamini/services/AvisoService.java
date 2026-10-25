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
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.mapStruct.EmpleoMapper;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvisoService {

    private final AvisoRepository avisoRepository;
    private final InmuebleMapper inmuebleMapper;          
    private final EmpleoMapper empleoMapper;               
    private final CompraVentaMapper compraVentaMapper;
    
    public List<AvisoResponseDto> listarTodos() {
    List<Aviso> avisos = avisoRepository.findAll();
    return mapearAvisos(avisos);
    }

    public List<AvisoResponseDto> buscarPorTitulo(String palabraClave) {
        List<Aviso> avisos = avisoRepository.findByTituloContainingIgnoreCase(palabraClave);
        return mapearAvisos(avisos); 
    }

    private List<AvisoResponseDto> mapearAvisos(List<Aviso> avisos) {
        return avisos.stream().map(aviso -> { // <-- Agregado el "return" aquí
            if (aviso instanceof Inmueble) {
                return (AvisoResponseDto) inmuebleMapper.toDto((Inmueble) aviso);
            } else if (aviso instanceof Empleo) {
                return (AvisoResponseDto) empleoMapper.toDto((Empleo) aviso);
            } else if (aviso instanceof CompraVenta) {
                return (AvisoResponseDto) compraVentaMapper.toDto((CompraVenta) aviso);
            }
            throw new IllegalStateException("Tipo de aviso desconocido: " + aviso.getClass());
        }).collect(Collectors.toList());
    }
}
    
