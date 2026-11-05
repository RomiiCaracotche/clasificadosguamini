package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaActualizadaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVenta.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.services.CompraVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController 
@RequestMapping("/api/compraventas")
@RequiredArgsConstructor
public class CompraVentaController {

    private final CompraVentaService compraVentaService;
    
    @GetMapping
    public ResponseEntity<List<CompraVentaResponseDto>> listarCompraVentasActivas() {
        return ResponseEntity.ok(compraVentaService.listarCompraVentasActivas());
    }

    @GetMapping("/todas")
    public ResponseEntity<List<CompraVentaResponseDto>> listarCompraVentas() {
        return ResponseEntity.ok(compraVentaService.listarCompraVentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraVentaResponseDto> mostrarCompraVenta(@PathVariable Long id) {
        return ResponseEntity.ok(compraVentaService.mostrarCompraVenta(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CompraVentaResponseDto> crearCompraVenta(@Valid @RequestPart("compraventa") CompraVentaRequestDto dto, @RequestPart(value = "imagenes") List<MultipartFile> archivos) {
        return ResponseEntity.status(HttpStatus.CREATED).body(compraVentaService.crearCompraVenta(dto, archivos));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CompraVentaResponseDto> modificarCompraVenta(@PathVariable Long id, @Valid @RequestPart("compraventa") CompraVentaActualizadaRequestDto dto, @RequestPart(value = "imagenes", required = false) List<MultipartFile> archivos) {
        return ResponseEntity.ok(compraVentaService.modificarCompraVenta(id, dto, archivos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompraVenta(@PathVariable Long id){
        return ResponseEntity.ok(compraVentaService.eliminarCompraVenta(id));
    }
    
}
