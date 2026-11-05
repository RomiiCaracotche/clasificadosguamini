package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.Empleo.EmpleoRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.EmpleoResponseDto;
import com.clasificados.clasificadosGuamini.services.EmpleoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/empleos")
@RequiredArgsConstructor
public class EmpleoController {

    private final EmpleoService empleoService;

    @GetMapping
    public ResponseEntity<List<EmpleoResponseDto>> listarEmpleosActivos() {
        return ResponseEntity.ok().body(empleoService.listarEmpleosActivos());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EmpleoResponseDto>> listarEmpleos() {
        return ResponseEntity.ok().body(empleoService.listarEmpleos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> mostrarEmpleo(@PathVariable Long id) {
        return ResponseEntity.ok().body(empleoService.mostrarEmpleo(id));
    }

    @PostMapping 
    public ResponseEntity<EmpleoResponseDto> crearEmpleo(@Valid @RequestBody EmpleoRequestDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(empleoService.crearEmpleo(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> modificarEmpleo(@PathVariable Long id, @RequestBody EmpleoRequestDto dto) {
        return ResponseEntity.ok().body(empleoService.modificarEmpleo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleo(@PathVariable Long id) {
        return ResponseEntity.ok().body(empleoService.eliminarEmpleo(id));
    }

}
