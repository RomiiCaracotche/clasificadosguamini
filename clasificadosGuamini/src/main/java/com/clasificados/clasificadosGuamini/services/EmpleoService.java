package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.repositories.EmpleoRepository;

@Service 
public class EmpleoService {

    private final EmpleoRepository empleoRepository;

    public EmpleoService(EmpleoRepository empleoRepository) {
        this.empleoRepository = empleoRepository;
    }

    public List<EmpleoResponseDto> listarEmpleos() {
        List<Empleo> listaEmpleos = empleoRepository.findAll();
        return listaEmpleos.stream().map(e -> mapearEntityADto(e)).toList();
    }

    public EmpleoResponseDto mostrarEmpleo(Long id) {
        Empleo empleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        return mapearEntityADto(empleo);
    }

    public EmpleoResponseDto crearEmpleo(EmpleoRequestDto dto){
        Empleo empleo = empleoRepository.save(mapearDtoAEntity(dto, null));
        return mapearEntityADto(empleo);
    }

    public EmpleoResponseDto modificarEmpleo(Long id, EmpleoRequestDto dto) {
        Empleo existeEmpleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        Empleo empleo = empleoRepository.save(mapearDtoAEntity(dto, existeEmpleo));
        return mapearEntityADto(empleo);
    }

    public String eliminarEmpleo(Long id) {
        Empleo existeEmpleo = empleoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el empleo con id: " + id));
        empleoRepository.delete(existeEmpleo);
        return "El empleo con id " + id + " se elimino correctamente";
    }


    private EmpleoResponseDto mapearEntityADto(Empleo entity) {
        EmpleoResponseDto dto = new EmpleoResponseDto();

        dto.setTipoEmpleo(entity.getTipoEmpleo());
        dto.setRubro(entity.getRubro());
        dto.setDisponibilidadHoraria(entity.getDisponibilidadHoraria());
        dto.setRequisitos(entity.getRequisitos());

        return dto;
    }

    private Empleo mapearDtoAEntity(EmpleoRequestDto dto, Empleo entityExiste) {
        if(entityExiste == null) {
            entityExiste = new Empleo();
        }

        entityExiste.setTipoEmpleo(dto.getTipoEmpleo());
        entityExiste.setRubro(dto.getRubro());
        entityExiste.setDisponibilidadHoraria(dto.getDisponibilidadHoraria());
        entityExiste.setRequisitos(dto.getRequisitos());
        
        return entityExiste;
        
    }

}
