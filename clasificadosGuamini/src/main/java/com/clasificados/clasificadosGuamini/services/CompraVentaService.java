package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;

@Service 
public class CompraVentaService {

    private final CompraVentaRepository compraVentaRepository;

    public CompraVentaService(CompraVentaRepository compraVentaRepository) {
        this.compraVentaRepository = compraVentaRepository;
    }

    public List<CompraVentaResponseDto> listarCompraVentas() {
        List<CompraVenta> listaCompraVenta = compraVentaRepository.findAll();
        return listaCompraVenta.stream().map(e -> mappearEntityADto(e)).toList();
    }

    public CompraVentaResponseDto mostrarCompraVenta(Long id) {
        CompraVenta compraVentaEntity = compraVentaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        return mappearEntityADto(compraVentaEntity);
    }

    public CompraVentaResponseDto crearCompraVenta(CompraVentaRequestDto compraventaDto) {
        CompraVenta compraVentaEntity = compraVentaRepository.save(mappearDtoAEntity(compraventaDto, null));
        return mappearEntityADto(compraVentaEntity);
    }

    public CompraVentaResponseDto modificarCompraVenta(Long id, CompraVentaRequestDto compraventaDto) {
        CompraVenta compraVentaEntidad = compraVentaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        CompraVenta compraVentaEntityModificada = compraVentaRepository.save(mappearDtoAEntity(compraventaDto, compraVentaEntidad));
        return mappearEntityADto(compraVentaEntityModificada);
    }

    public String eliminarCompraVenta(Long id){
        CompraVenta compraVenta = compraVentaRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el id: " + id));
        compraVentaRepository.delete(compraVenta);
        return "La compraventa se eliminó correctamente";
    }


    private CompraVenta mappearDtoAEntity(CompraVentaRequestDto dto, CompraVenta entidad) {
        if(entidad == null) {
            entidad = new CompraVenta();
        }
        
        entidad.setTitulo(dto.getTitulo());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setLocalidad(dto.getLocalidad());
        entidad.setAnunciante(dto.getAnunciante());
        
        entidad.setCategoria(dto.getCategoria());
        entidad.setPrecio(dto.getPrecio());
        entidad.setEstado(dto.getEstado());
        entidad.setMarca(dto.getMarca());
        entidad.setModelo(dto.getModelo());
        entidad.setColor(dto.getColor());

        return entidad;
    }

    private CompraVentaResponseDto mappearEntityADto(CompraVenta entidad) {
        CompraVentaResponseDto dto = new CompraVentaResponseDto();

        dto.setId(entidad.getId());
        dto.setTitulo(entidad.getTitulo());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setFecha(entidad.getFecha());
        dto.setLocalidad(entidad.getLocalidad());
        dto.setAnunciante(entidad.getAnunciante());
        
        dto.setCategoria(entidad.getCategoria());
        dto.setPrecio(entidad.getPrecio());
        dto.setEstado(entidad.getEstado());
        dto.setMarca(entidad.getMarca());
        dto.setModelo(entidad.getModelo());
        dto.setColor(entidad.getColor());

        return dto;
    }

}
