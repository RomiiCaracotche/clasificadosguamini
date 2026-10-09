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
import com.clasificados.clasificadosGuamini.dtos.request.UsuarioRequestDto;
import com.clasificados.clasificadosGuamini.dtos.response.UsuarioResponseDto;
import com.clasificados.clasificadosGuamini.services.UsuarioService;

@RestController 
@RequestMapping("/api/usuarios")
public class UsuarioController {
 
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping 
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> mostrarUsuario(@PathVariable Long id) {
        return null;
    }

    @PostMapping 
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@RequestBody UsuarioRequestDto empleo){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> modificarUsuario(@PathVariable Long id, @RequestBody UsuarioRequestDto empleo) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        return null;
    }
}
