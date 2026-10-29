package com.clasificados.clasificadosGuamini.controllers;

import java.security.Principal;
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
import com.clasificados.clasificadosGuamini.dtos.request.InmuebleRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.InmuebleResponseDto;
import com.clasificados.clasificadosGuamini.services.InmuebleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/inmuebles")
@RequiredArgsConstructor
public class InmuebleController {

    private final InmuebleService inmuebleService;

    @GetMapping
    public ResponseEntity<List<InmuebleResponseDto>> listarInmuebles() {
        List<InmuebleResponseDto> listaInmuebles = inmuebleService.listarInmuebles();
        return ResponseEntity.ok().body(listaInmuebles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InmuebleResponseDto> mostrarInmueble(@PathVariable Long id) {
        InmuebleResponseDto dto = inmuebleService.mostrarInmueble(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping 
    public ResponseEntity<InmuebleResponseDto> crearInmueble(@Valid @RequestBody InmuebleRequestDto inmueble, Principal principal){
        InmuebleResponseDto dto = inmuebleService.crearInmueble(principal.getName(), inmueble);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InmuebleResponseDto> modificarInmueble(@PathVariable Long id, @RequestBody InmuebleRequestDto inmueble) {
        InmuebleResponseDto dto = inmuebleService.modificarInmueble(id, inmueble);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInmueble(@PathVariable Long id, Principal principal) {
        String inmueble = inmuebleService.eliminarInmueble(principal.getName(), id);
        return ResponseEntity.ok().body(inmueble);
    }
}
