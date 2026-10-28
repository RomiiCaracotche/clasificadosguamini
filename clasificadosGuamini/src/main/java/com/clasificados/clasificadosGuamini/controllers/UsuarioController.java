package com.clasificados.clasificadosGuamini.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
 
    private final UsuarioService usuarioService;

    @GetMapping 
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return ResponseEntity.ok().body(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponseDto> mostrarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioService.mostrarUsuario(id));
    }

    @PostMapping 
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioRequestDto dto, BindingResult result){
        if(result.hasFieldErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> modificarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto dto) {
        return ResponseEntity.ok(usuarioService.modificarUsuario(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.eliminarUsuario(id));
    }


    
    @PostMapping("/{idUsuario}/favoritos/{idAviso}")
    public ResponseEntity<Void> agregarFavorito(@PathVariable Long idUsuario, @PathVariable Long idAviso) {
        usuarioService.agregarFavorito(idUsuario, idAviso);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idUsuario}/favoritos/{idAviso}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Long idUsuario, @PathVariable Long idAviso) {
        usuarioService.eliminarFavorito(idUsuario, idAviso);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUsuario}/favoritos")
    public ResponseEntity<Set<AvisoResponseDto>> obtenerFavoritos(@PathVariable Long idUsuario) {
        Set<AvisoResponseDto> favoritos = usuarioService.obtenerFavoritos(idUsuario);
        return ResponseEntity.ok(favoritos);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    
}
