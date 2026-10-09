package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.services.EmpleoService;

@RestController 
@RequestMapping("/api/empleos")
public class EmpleoController {

    private EmpleoService empleoService;

    public EmpleoController(EmpleoService empleoService) {
        this.empleoService = empleoService;
    }

    @GetMapping 
    public ResponseEntity<List<EmpleoResponseDto>> listarEmpleos() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> mostrarEmpleo(@PathVariable Long id) {
        return null;
    }

    @PostMapping 
    public ResponseEntity<EmpleoResponseDto> crearEmpleo(@RequestBody EmpleoRequestDto empleo){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> modificarEmpleo(@PathVariable Long id, @RequestBody EmpleoRequestDto empleo) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleo(@PathVariable Long id) {
        return null;
    }
}
