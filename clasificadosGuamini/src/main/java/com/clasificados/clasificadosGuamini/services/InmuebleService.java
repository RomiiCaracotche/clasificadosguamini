package com.clasificados.clasificadosGuamini.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import com.clasificados.clasificadosGuamini.mapStruct.InmuebleMapper;
import com.clasificados.clasificadosGuamini.repositories.InmuebleRepository;
import com.clasificados.clasificadosGuamini.utils.FileStorageUtils;
import com.clasificados.clasificadosGuamini.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InmuebleService {

    private final InmuebleRepository inmuebleRepository;
    private final InmuebleMapper inmuebleMapper;
    private SecurityUtils securityUtils;
    private final FileStorageUtils fileStorageUtils;

    public List<InmuebleResponseDto> listarInmueblesActivos() {
        LocalDateTime ahora = LocalDateTime.now();
        List<Inmueble> listaInmuebles = inmuebleRepository.findAllActivos(EstadoAviso.APROBADO, ahora);
        return listaInmuebles.stream().map(e -> inmuebleMapper.toDto(e)).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<InmuebleResponseDto> listarInmuebles() {
        List<Inmueble> listaInmuebles = inmuebleRepository.findAll();
        return listaInmuebles.stream().map(e -> inmuebleMapper.toDto(e)).toList();
    }

    public InmuebleResponseDto mostrarInmueble(Long id) {
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public InmuebleResponseDto crearInmueble(InmuebleRequestDto dto, List<MultipartFile> archivos){
        Usuario usuario = securityUtils.obtenerUsuarioActual();
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

        Inmueble inmueble = inmuebleMapper.toEntity(dto, usuario);
        inmueble.setAnunciante(usuario);
        Inmueble inmuebleGuardado = inmuebleRepository.save(inmueble);
        return inmuebleMapper.toDto(inmuebleGuardado);
    }

    @Transactional 
    public InmuebleResponseDto modificarInmueble(Long id, InmuebleRequestDto dtoModificado) {
        Usuario usuario = securityUtils.obtenerUsuarioActual();
        securityUtils.verificarPropietarioOAdmin(usuario.getId(), id);
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleMapper.updateEntityFromDto(dtoModificado, inmueble);
        return inmuebleMapper.toDto(inmueble);
    }

    @Transactional 
    public String eliminarInmueble(Long id) {
        Usuario usuario = securityUtils.obtenerUsuarioActual();
        securityUtils.verificarPropietarioOAdmin(usuario.getId(), id);
        Inmueble inmueble = inmuebleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el inmueble con id: " + id));
        inmuebleRepository.delete(inmueble);
        return "El inmueble con id " + id + " se elimino correctamente";
    }

}
