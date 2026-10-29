package com.clasificados.clasificadosGuamini.services.Admin;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.mapStruct.EmpleoMapper;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AvisoAdminService {

    private final AvisoRepository avisoRepository;
    private final InmuebleMapper inmuebleMapper;
    private final EmpleoMapper empleoMapper;
    private final CompraVentaMapper compraVentaMapper;

    @Transactional(readOnly = true)
    public List<AvisoResponseDto> listarPendientes() {
        List<Aviso> avisosPendientes = avisoRepository.findByEstado(EstadoAviso.PENDIENTE);
        return avisosPendientes.stream().map(this::mapearInstancia).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AvisoResponseDto> listarAprobados() {
        List<Aviso> avisosAprobados = avisoRepository.findByEstado(EstadoAviso.APROBADO);
        return avisosAprobados.stream().map(this::mapearInstancia).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AvisoResponseDto> listarRechazados() {
        List<Aviso> avisosRechazados = avisoRepository.findByEstado(EstadoAviso.RECHAZADO);
        return avisosRechazados.stream().map(this::mapearInstancia).collect(Collectors.toList());
    }

    private AvisoResponseDto mapearInstancia(Aviso aviso) {
        if (aviso instanceof Inmueble) {
            return inmuebleMapper.toDto((Inmueble) aviso);
        } else if (aviso instanceof Empleo) {
            return empleoMapper.toDto((Empleo) aviso);
        } else if (aviso instanceof CompraVenta) {
            return compraVentaMapper.toDto((CompraVenta) aviso);
        }
        throw new IllegalStateException("Tipo de aviso desconocido: " + aviso.getClass());
    }

    @Transactional
    public void aprobarAviso(Long id) {
        Aviso aviso = avisoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el aviso con id: " + id));
        aviso.setEstado(EstadoAviso.APROBADO);
        avisoRepository.save(aviso);
    }

    @Transactional
    public void rechazarAviso(Long id) {
        Aviso aviso = avisoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el aviso con id: " + id));
        aviso.setEstado(EstadoAviso.RECHAZADO);
        avisoRepository.save(aviso);
    }

    @Transactional
    public long cantidadAvisos() {
        return avisoRepository.count();
    }

}
