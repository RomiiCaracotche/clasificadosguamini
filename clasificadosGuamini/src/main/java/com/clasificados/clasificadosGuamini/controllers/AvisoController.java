package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.services.AvisoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/avisos")
@RequiredArgsConstructor
public class AvisoController {

    private final AvisoService avisoService;

    @GetMapping
    public ResponseEntity<List<AvisoResponseDto>> listarTodosLosAvisos() {
        List<AvisoResponseDto> avisos = avisoService.listarTodos();
        return ResponseEntity.ok(avisos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AvisoResponseDto>> buscarGlobal(@RequestParam String palabraClave) {
        List<AvisoResponseDto> resultados = avisoService.buscarPorTitulo(palabraClave);
        return ResponseEntity.ok(resultados);
    }
    
}
