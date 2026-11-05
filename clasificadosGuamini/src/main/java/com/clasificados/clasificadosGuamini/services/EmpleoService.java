package com.clasificados.clasificadosGuamini.services;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.Empleo.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.enums.ModalidadEmpleo;
import com.clasificados.clasificadosGuamini.mapStruct.EmpleoMapper;
import com.clasificados.clasificadosGuamini.repositories.EmpleoRepository;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpleoService {

    private final EmpleoRepository empleoRepository;
    private final EmpleoMapper empleoMapper;
    private SecurityUtils securityUtils;

    public List<EmpleoResponseDto> listarEmpleosActivos() {
        LocalDateTime ahora = LocalDateTime.now();
        List<Empleo> listaEmpleos = empleoRepository.findAllActivos(EstadoAviso.APROBADO, ahora);
        return empleoMapper.toResponseDtoList(listaEmpleos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<EmpleoResponseDto> listarEmpleos() {
        List<Empleo> listaEmpleos = empleoRepository.findAll();
        return empleoMapper.toResponseDtoList(listaEmpleos);
    }

    public EmpleoResponseDto mostrarEmpleo(Long id) {
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public EmpleoResponseDto crearEmpleo(EmpleoRequestDto dto){
        Usuario usuario = securityUtils.obtenerUsuarioActual();
        Empleo empleo = empleoMapper.toEntity(dto, usuario);
        empleo.setAnunciante(usuario);

        String imagenPorDefecto = ModalidadEmpleo.BUSCO_EMPLEO.equals(empleo.getModalidadEmpleo())
        ? "/uploads/defaults/busco_empleo.jpg" 
        : "/uploads/defaults/oferta_empleo.jpg";

        empleo.setImagenes(List.of(imagenPorDefecto));
        Empleo empleoGuardado = empleoRepository.save(empleo);
        return empleoMapper.toDto(empleoGuardado);
    }

    @Transactional 
    public EmpleoResponseDto modificarEmpleo(Long id, EmpleoRequestDto dtoModificado) {
        securityUtils.verificarPropietarioOAdmin(id);
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoMapper.updateEntityFromDto(dtoModificado, empleo);
        return empleoMapper.toDto(empleo);
    }

    @Transactional 
    public String eliminarEmpleo(Long id) {
        securityUtils.verificarPropietarioOAdmin(id);        
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoRepository.delete(empleo);
        return "El empleo con id " + id + " se elimino correctamente";
    }

   

}
