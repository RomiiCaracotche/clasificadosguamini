package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
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

    private final EmpleoService empleoService;

    public EmpleoController(EmpleoService empleoService) {
        this.empleoService = empleoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EmpleoResponseDto>> listarEmpleos() {
        List<EmpleoResponseDto> listaEmpleados = empleoService.listarEmpleos();
        return ResponseEntity.ok().body(listaEmpleados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> mostrarEmpleo(@PathVariable Long id) {
        EmpleoResponseDto dto = empleoService.mostrarEmpleo(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping 
    public ResponseEntity<EmpleoResponseDto> crearEmpleo(@RequestBody EmpleoRequestDto empleo){
        EmpleoResponseDto dto = empleoService.crearEmpleo(empleo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleoResponseDto> modificarEmpleo(@PathVariable Long id, @RequestBody EmpleoRequestDto empleo) {
        EmpleoResponseDto dto = empleoService.modificarEmpleo(id, empleo);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleo(@PathVariable Long id) {
        String empleoEliminado = empleoService.eliminarEmpleo(id);
        return ResponseEntity.ok().body(empleoEliminado);
    }

}
