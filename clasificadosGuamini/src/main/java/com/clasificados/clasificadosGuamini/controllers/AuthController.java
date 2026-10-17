package com.clasificados.clasificadosGuamini.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.Exception.dtos.ResourceNotFoundException;
import com.clasificados.clasificadosGuamini.Security.JwtService;
import com.clasificados.clasificadosGuamini.dtos.request.LoginRequestDto;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    
    private UsuarioRepository usuarioRepository;
    
    private JwtService jwtService;


    public AuthController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getNombreUsuario(), dto.getPassword()));
        Usuario usuarioEntity = usuarioRepository.findByNombreUsuario(dto.getNombreUsuario()).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        String jwtToken = jwtService.generateToken(usuarioEntity);
        return ResponseEntity.ok(jwtToken);
    }
}
