package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.ReporteAvisoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.services.AvisoService;
import com.clasificados.clasificadosGuamini.services.ReporteAvisoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/avisos")
@RequiredArgsConstructor
public class AvisoController {

    private final AvisoService avisoService;
    private final ReporteAvisoService reporteAvisoService;

    @GetMapping
    public ResponseEntity<List<AvisoResponseDto>> listarAprobados() {
        return ResponseEntity.ok().body(avisoService.listarAprobados());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<AvisoResponseDto>> buscarPorPalabraClave(@RequestParam String palabraClave) {
        return ResponseEntity.ok(avisoService.buscarPorPalabraClave(palabraClave));
    }

    @PostMapping("/reportar/{id}")
    public ResponseEntity<Void> reportarAviso(@PathVariable Long id, @Valid @RequestBody ReporteAvisoRequestDto dto) {
        reporteAvisoService.reportarAviso(id, dto);
        return ResponseEntity.noContent().build();
    }

}
