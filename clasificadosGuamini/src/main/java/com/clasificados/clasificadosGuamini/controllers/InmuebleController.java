package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.clasificados.clasificadosGuamini.dtos.request.Inmueble.InmuebleActualizadoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.Inmueble.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.services.InmuebleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/inmuebles")
@RequiredArgsConstructor
public class InmuebleController {

    private final InmuebleService inmuebleService;

    @GetMapping
    public ResponseEntity<List<InmuebleResponseDto>> listarInmueblesActivos() {
        return ResponseEntity.ok().body(inmuebleService.listarInmueblesActivos());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<InmuebleResponseDto>> listarInmuebles() {
        return ResponseEntity.ok().body(inmuebleService.listarInmuebles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InmuebleResponseDto> mostrarInmueble(@PathVariable Long id) {
        return ResponseEntity.ok().body(inmuebleService.mostrarInmueble(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InmuebleResponseDto> crearInmueble(@Valid @RequestPart("inmueble") InmuebleRequestDto dto, @RequestPart(value = "imagenes") List<MultipartFile> archivos){
        return ResponseEntity.status(HttpStatus.CREATED).body(inmuebleService.crearInmueble(dto, archivos));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InmuebleResponseDto> modificarInmueble(@PathVariable Long id, @Valid @RequestPart("inmueble") InmuebleActualizadoRequestDto dto, @RequestPart(value = "imagenes", required = false) List<MultipartFile> archivos) {
        return ResponseEntity.ok().body(inmuebleService.modificarInmueble(id, dto, archivos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInmueble(@PathVariable Long id) {
        return ResponseEntity.ok().body(inmuebleService.eliminarInmueble(id));
    }
}
