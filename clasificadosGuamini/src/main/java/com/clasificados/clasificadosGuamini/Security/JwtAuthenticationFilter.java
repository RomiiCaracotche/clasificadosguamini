package com.clasificados.clasificadosGuamini.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.clasificados.clasificadosGuamini.dtos.request.LoginRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;
import static com.clasificados.clasificadosGuamini.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/api/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String nombreUsuario = null;
        String password = null;
        try {
            LoginRequestDto loginDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            nombreUsuario = loginDto.getNombreUsuario();
            password = loginDto.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(nombreUsuario, password);
        return authenticationManager.authenticate(authenticationToken); 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        
        User user = (User) authResult.getPrincipal();
        String username = user.getUsername();
        List<String> roles = authResult.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

        Claims claims = Jwts.claims()
                        .add("authorities", roles) 
                        .build();

        String token = Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        /* La hora actual + 1 hora */
                        .expiration(new Date(System.currentTimeMillis() + 3600000))
                        .issuedAt(new Date())
                        .signWith(SECRET_KEY)
                        .compact();
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s has iniciado sesion con exito", username));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en la autenticacion, usuario o contraseña incorrecta");
        body.put("error", failed.getMessage());
        
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(401);
    
    }

}
