package com.clasificados.clasificadosGuamini.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.request.LoginRequestDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    /* private final AuthenticationManager authenticationManager; */
    // Inyectas tu servicio de autenticación o generador de tokens JWT aquí

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginDto) {
        // Lógica para autenticar credenciales y retornar el token
        return null;
    }
}
