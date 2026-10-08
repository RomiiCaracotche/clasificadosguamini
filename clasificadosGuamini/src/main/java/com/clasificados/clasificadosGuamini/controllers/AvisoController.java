package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;

@RestController
@RequestMapping("/api/avisos")
public class AvisoController {

    private final AvisoRepository avisoRepository;

    public AvisoController(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    // 1. LISTAR TODO: Devuelve inmuebles, productos y empleos mezclados
    @GetMapping
    public ResponseEntity<List<Aviso>> listarTodosLosAvisos() {
        return null;
    }

    // 2. BUSCADOR GLOBAL: Busca por título en cualquier categoría
    @GetMapping("/buscar")
    public ResponseEntity<List<Aviso>> buscarGlobal(@RequestParam String palabraClave) {
        return null;
    }
}
