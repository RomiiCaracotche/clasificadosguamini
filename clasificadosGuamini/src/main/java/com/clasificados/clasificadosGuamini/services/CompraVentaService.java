package com.clasificados.clasificadosGuamini.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompraVentaService {

    private final CompraVentaRepository compraVentaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CompraVentaMapper compraVentaMapper;
    private final SecurityUtils securityUtils;

    public List<CompraVentaResponseDto> listarCompraVentas() {
        List<CompraVenta> listaCompraVenta = compraVentaRepository.findAll();
        return listaCompraVenta.stream().map(e -> compraVentaMapper.toDto(e)).toList();
    }

    public CompraVentaResponseDto mostrarCompraVenta(Long id) {
        CompraVenta compraVenta = compraVentaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        return compraVentaMapper.toDto(compraVenta);
    }

    @Transactional
    public CompraVentaResponseDto crearCompraVenta(String nombreUsuarioActual, CompraVentaRequestDto dto) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        CompraVenta compraventa = compraVentaMapper.toEntity(dto, usuario);
        compraventa.setAnunciante(usuario);
        CompraVenta compraVentaGuardada = compraVentaRepository.save(compraventa);
        return compraVentaMapper.toDto(compraVentaGuardada);
    }

    @Transactional
    public CompraVentaResponseDto modificarCompraVenta(String nombreUsuarioActual, Long id, CompraVentaRequestDto dtoModificado) {
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);
        CompraVenta compraventa = compraVentaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        compraVentaMapper.updateEntityFromDto(dtoModificado, compraventa);
        /* CompraVenta compraVentaEntityGuardada = compraVentaRepository.save(compraventa); */
        return compraVentaMapper.toDto(compraventa);
    }

    @Transactional
    public String eliminarCompraVenta(String nombreUsuarioActual, Long id){
        securityUtils.verificarPropietarioOAdmin(nombreUsuarioActual, id);
        CompraVenta compraventa = compraVentaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + id));
        compraVentaRepository.delete(compraventa);
        return "La compraventa con id " + id + " se eliminó correctamente";
    }

}
