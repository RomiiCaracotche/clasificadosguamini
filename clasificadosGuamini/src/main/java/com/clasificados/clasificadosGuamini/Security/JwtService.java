package com.clasificados.clasificadosGuamini.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    // 1. Generar el token a partir de la autenticación exitosa
    public String generateToken(Authentication authResult) {
        User user = (User) authResult.getPrincipal();
        String username = user.getUsername();
        
        List<String> roles = authResult.getAuthorities().stream()
                .map(authority -> authority.getAuthority())
                .toList();

        return Jwts.builder()
                .subject(username)
                .claim("authorities", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(TokenJwtConfig.SECRET_KEY)
                .compact();
    }

    // 2. Extraer los claims (el payload completo)
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(TokenJwtConfig.SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 3. Extraer el username del token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 4. Validar si el token es correcto y no ha expirado
    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
