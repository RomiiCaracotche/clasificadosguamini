package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.services.AvisoService;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {

    private final AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    // 1. LISTAR TODO: Devuelve inmuebles, productos y empleos mezclados
    @GetMapping
    public ResponseEntity<List<AvisoResponseDto>> listarTodosLosAvisos() {
        return null;
    }

    // 2. BUSCADOR GLOBAL: Busca por título en cualquier categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<AvisoResponseDto>> buscarGlobal(@RequestParam String palabraClave) {
        return null;
    }
}
