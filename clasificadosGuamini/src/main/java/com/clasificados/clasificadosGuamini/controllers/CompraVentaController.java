package com.clasificados.clasificadosGuamini.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.services.CompraVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController 
@RequestMapping("/api/compraventas")
@RequiredArgsConstructor
public class CompraVentaController {

    private final CompraVentaService compraVentaService;
    
    @GetMapping
    public ResponseEntity<List<CompraVentaResponseDto>> listarCompraVentas() {
        List<CompraVentaResponseDto> listaCompraVenta = compraVentaService.listarCompraVentas();
        return ResponseEntity.ok(listaCompraVenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraVentaResponseDto> mostrarCompraVenta(@PathVariable Long id) {
        CompraVentaResponseDto dto = compraVentaService.mostrarCompraVenta(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CompraVentaResponseDto> crearCompraVenta(@Valid @RequestBody CompraVentaRequestDto compraventa, Principal principal) {
        CompraVentaResponseDto dto = compraVentaService.crearCompraVenta(principal.getName(), compraventa);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraVentaResponseDto> modificarCompraVenta(@PathVariable Long id, @Valid @RequestBody CompraVentaRequestDto compraventa, Principal principal) {
        CompraVentaResponseDto dto = compraVentaService.modificarCompraVenta(principal.getName(), id, compraventa);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompraVenta(@PathVariable Long id, Principal principal){
        String compraventa = compraVentaService.eliminarCompraVenta(principal.getName(), id);
        return ResponseEntity.ok(compraventa);
    }
    
}
