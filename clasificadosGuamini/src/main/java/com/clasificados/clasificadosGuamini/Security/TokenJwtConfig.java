package com.clasificados.clasificadosGuamini.security;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenJwtConfig {

    /* Si se cae el servidor con esta opcion aparece error de autenticacion porque la clave cambia */
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build(); 
    
    /* La clase no cambia sin importar si se reinicia el servidor */
    /* public static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor("clave-secreta-super-segura-para-nuestro-sistema-clasificados-guamini-2026".getBytes(StandardCharsets.UTF_8)); */
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
    
}
