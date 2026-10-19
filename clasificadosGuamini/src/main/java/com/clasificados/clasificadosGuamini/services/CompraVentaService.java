package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.exception.dtos.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@Service 
@Transactional(readOnly = true)
public class CompraVentaService {

    private final CompraVentaRepository compraVentaRepository;
    private final UsuarioRepository usuarioRepository;
    private CompraVentaMapper compraVentaMapper;

    public CompraVentaService(CompraVentaRepository compraVentaRepository, UsuarioRepository usuarioRepository, CompraVentaMapper compraVentaMapper) {
        this.compraVentaRepository = compraVentaRepository;
        this.usuarioRepository = usuarioRepository;
        this.compraVentaMapper = compraVentaMapper;
    }

    public List<CompraVentaResponseDto> listarCompraVentas() {
        List<CompraVenta> listaCompraVenta = compraVentaRepository.findAll();
        return listaCompraVenta.stream().map(e -> compraVentaMapper.toDto(e)).toList();
    }

    public CompraVentaResponseDto mostrarCompraVenta(Long id) {
        CompraVenta compraVenta = compraVentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        return compraVentaMapper.toDto(compraVenta);
    }

    @Transactional
    public CompraVentaResponseDto crearCompraVenta(CompraVentaRequestDto compraventaDto) {
        CompraVenta compraVenta = compraVentaRepository.save(compraVentaMapper.toEntity(compraventaDto));
        return compraVentaMapper.toDto(compraVenta);
    }

    @Transactional
    public CompraVentaResponseDto modificarCompraVenta(Long id, CompraVentaRequestDto dtoModificado) {
        CompraVenta compraVentaExistente = compraVentaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        compraVentaMapper.updateEntityFromDto(dtoModificado, compraVentaExistente);
        CompraVenta compraVentaEntityGuardada = compraVentaRepository.save(compraVentaExistente);
        return compraVentaMapper.toDto(compraVentaEntityGuardada);
    }

    @Transactional
    public String eliminarCompraVenta(Long id){
        CompraVenta compraVenta = compraVentaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        compraVentaRepository.delete(compraVenta);
        return "La compraventa con id " + id + " se eliminó correctamente";
    }

}
