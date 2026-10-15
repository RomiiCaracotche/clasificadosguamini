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
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.services.UsuarioService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/usuarios")
public class UsuarioController {
 
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        List<UsuarioResponseDto> UsuariosDto = usuarioService.listarUsuarios();
        return ResponseEntity.ok().body(UsuariosDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> mostrarUsuario(@PathVariable Long id) {
        UsuarioResponseDto UsuarioDto = usuarioService.mostrarUsuario(id);
        return ResponseEntity.ok().body(UsuarioDto);
    }

    @PostMapping 
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@Valid @RequestBody UsuarioRequestDto dto){
        UsuarioResponseDto usuarioDto = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> modificarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto usuarioDto = usuarioService.modificarUsuario(id, dto);
        return ResponseEntity.ok(usuarioDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        String usuarioEliminado = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(usuarioEliminado);
    }
    
}
