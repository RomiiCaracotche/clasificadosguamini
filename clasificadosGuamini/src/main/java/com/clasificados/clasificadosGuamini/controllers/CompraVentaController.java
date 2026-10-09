package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.clasificados.clasificadosGuamini.dtos.request.CompraVentaRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.CompraVentaResponseDto;
import com.clasificados.clasificadosGuamini.services.CompraVentaService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@Controller 
@RequestMapping("/api/compraventa")
public class CompraVentaController {

    private final CompraVentaService compraVentaService;

    public CompraVentaController(CompraVentaService compraVentaService){
        this.compraVentaService = compraVentaService;
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CompraVentaResponseDto>> listarCompraVentas() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraVentaResponseDto> mostrarCompraVenta(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    public ResponseEntity<CompraVentaResponseDto> crearCompraVenta(@RequestBody CompraVentaRequestDto compraventa) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompraVentaResponseDto> modificarCompraVenta(@PathVariable Long id, @RequestBody CompraVentaRequestDto compraventa) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCompraVenta(@PathVariable Long id){
        return null;
    }
    
    
    
}
