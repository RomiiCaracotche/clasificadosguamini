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
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.services.InmuebleService;

@RestController 
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    private final InmuebleService inmuebleService;

    public InmuebleController(InmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
    }

    @GetMapping 
    public ResponseEntity<List<InmuebleResponseDto>> listarInmuebles() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<InmuebleResponseDto> mostrarInmueble(@PathVariable Long id) {
        return null;
    }

    @PostMapping 
    public ResponseEntity<InmuebleResponseDto> crearInmueble(@RequestBody InmuebleRequestDto inmueble){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<InmuebleResponseDto> modificarInmueble(@PathVariable Long id, @RequestBody InmuebleRequestDto inmueble) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInmueble(@PathVariable Long id) {
        return null;
    }
}
