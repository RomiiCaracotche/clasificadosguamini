package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.repositories.InmuebleRepository;

@Service
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;

    public InmuebleService(InmuebleRepository inmuebleRepository){
        this.inmuebleRepository = inmuebleRepository;
    }

    public List<InmuebleResponseDto> listarInmuebles() {
        List<Inmueble> listaInmuebles = inmuebleRepository.findAll();
        return listaInmuebles.stream().map(e -> mapearEntityADto(e)).toList();
    }


    public InmuebleResponseDto mostrarInmueble(Long id) {
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        return mapearEntityADto(inmueble);
    }

    public InmuebleResponseDto crearInmueble(InmuebleRequestDto dto){
         Inmueble inmueble = inmuebleRepository.save(mapearDtoAEntity(dto, null));
         return mapearEntityADto(inmueble);

    }

    public InmuebleResponseDto modificarInmueble(Long id, InmuebleRequestDto to) {
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        return mapearEntityADto(inmueble);
    }

    public String eliminarInmueble(Long id) {
        Inmueble existeInmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleRepository.delete(existeInmueble);
        return "El inmueble con id " + id + " se elimino correctamente";
    }


    private InmuebleResponseDto mapearEntityADto(Inmueble entity) {
        InmuebleResponseDto dto = new InmuebleResponseDto();

        dto.setTipoOperacion(entity.getTipoOperacion());
        dto.setTipoVivienda(entity.getTipoVivienda());
        dto.setPrecio(entity.getPrecio());
        dto.setAmbientes(entity.getAmbientes());
        dto.setHabitaciones(entity.getHabitaciones());
        dto.setPatio(entity.getPatio());
        dto.setBanos(entity.getBanos());
        dto.setMascotas(entity.getMascotas());
        dto.setExpensas(entity.getExpensas());
        dto.setDireccion(entity.getDireccion());

        return dto;
    }

    private Inmueble mapearDtoAEntity(InmuebleRequestDto dto, Inmueble entityExiste) {
        if(entityExiste == null) {
            entityExiste = new Inmueble();
        }

        entityExiste.setTipoOperacion(dto.getTipoOperacion());
        entityExiste.setTipoVivienda(dto.getTipoVivienda());
        entityExiste.setPrecio(dto.getPrecio());
        entityExiste.setAmbientes(dto.getAmbientes());
        entityExiste.setHabitaciones(dto.getHabitaciones());
        entityExiste.setPatio(dto.getPatio());
        entityExiste.setBanos(dto.getBanos());
        entityExiste.setMascotas(dto.getMascotas());
        entityExiste.setExpensas(dto.getExpensas());
        entityExiste.setDireccion(dto.getDireccion());
        
        return entityExiste;
        
    }

}
