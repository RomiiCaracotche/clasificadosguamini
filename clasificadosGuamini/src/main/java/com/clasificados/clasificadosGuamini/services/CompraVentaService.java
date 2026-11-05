package com.clasificados.clasificadosGuamini.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaActualizadaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.exception.personalizadas.BadRequestException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.mapStruct.CompraVentaMapper;
import com.clasificados.clasificadosGuamini.repositories.CompraVentaRepository;
import com.clasificados.clasificadosGuamini.utils.FileStorageUtils;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@Service 
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompraVentaService {

    private final CompraVentaRepository compraVentaRepository;
    private final CompraVentaMapper compraVentaMapper;
    private final SecurityUtils securityUtils;
    private final FileStorageUtils fileStorageUtils;
    

    public List<CompraVentaResponseDto> listarCompraVentasActivas() {
        LocalDateTime ahora = LocalDateTime.now();
        List<CompraVenta> listaCompraVenta = compraVentaRepository.findAllActivos(EstadoAviso.APROBADO, ahora);
        return compraVentaMapper.toResponseDtoList(listaCompraVenta);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<CompraVentaResponseDto> listarCompraVentas() {
        List<CompraVenta> listaCompraVenta = compraVentaRepository.findAll();
        return compraVentaMapper.toResponseDtoList(listaCompraVenta);
    }

    public CompraVentaResponseDto mostrarCompraVenta(Long idAviso) {
        CompraVenta compraVenta = compraVentaRepository.findById(idAviso).orElseThrow(() -> new ResourceNotFoundException("No se encontró la compraventa con id: " + idAviso));
        return compraVentaMapper.toDto(compraVenta);
    }

    @Transactional
    public CompraVentaResponseDto crearCompraVenta(CompraVentaRequestDto dto, List<MultipartFile> archivos) {
        Usuario usuarioActual = securityUtils.obtenerUsuarioActual();

        if (archivos == null || archivos.isEmpty()) {
            throw new BadRequestException("Debe subir al menos una imagen para crear la compraventa.");
        }
        List<String> imagenes = new ArrayList<>();
            for (MultipartFile archivo : archivos) {
                if (!archivo.isEmpty()) {
                try {
                        String rutaRelativa = fileStorageUtils.guardarArchivoEnDisco(archivo);
                        imagenes.add(rutaRelativa);
                    } catch (IOException e) {
                        throw new RuntimeException("Error al guardar la imagen: " + e.getMessage());
                    }
                }
            }

        CompraVenta compraventa = compraVentaMapper.toEntity(dto, usuarioActual);
        compraventa.setAnunciante(usuarioActual);
        compraventa.setImagenes(imagenes);
        CompraVenta compraVentaGuardada = compraVentaRepository.save(compraventa);
        return compraVentaMapper.toDto(compraVentaGuardada);
    }


    @Transactional
    public CompraVentaResponseDto modificarCompraVenta(Long idAviso, CompraVentaActualizadaRequestDto dtoModificado, List<MultipartFile> archivos) {
        securityUtils.verificarPropietarioOAdmin(idAviso);
        CompraVenta compraventa = compraVentaRepository.findById(idAviso).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + idAviso));
        compraVentaMapper.updateEntityFromDto(dtoModificado, compraventa);
    
        // Solo si el usuario envió nuevos archivos, actualizamos la lista de imágenes.
        if (archivos != null && !archivos.isEmpty()) {
            List<String> nuevasImagenes = new ArrayList<>();
            for (MultipartFile archivo : archivos) {
                if (!archivo.isEmpty()) {
                    try {
                        String rutaRelativa = fileStorageUtils.guardarArchivoEnDisco(archivo);
                        nuevasImagenes.add(rutaRelativa);
                    } catch (IOException e) {
                        throw new RuntimeException("Error al guardar la nueva imagen: " + e.getMessage());
                    }
                }
            }
            // Reemplazamos solo si se subieron nuevas
            if (!nuevasImagenes.isEmpty()) {
                compraventa.setImagenes(nuevasImagenes);
            }
        }

        return compraVentaMapper.toDto(compraVentaRepository.save(compraventa));
    }

    @Transactional
    public String eliminarCompraVenta(Long idAviso){
        securityUtils.verificarPropietarioOAdmin(idAviso);
        CompraVenta compraventa = compraVentaRepository.findById(idAviso).orElseThrow(()->new ResourceNotFoundException("No se encontró la compraventa con id: " + idAviso));
        compraVentaRepository.delete(compraventa);
        return "La compraventa con id " + idAviso + " se eliminó correctamente";
    }

}
