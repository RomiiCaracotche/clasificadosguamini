package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioResponseDto {

    private Long id;

    private String nombre;

    private String apellido;

    private String dni;

    private String nombreUsuario;

    private String email;

    private String celular;

    private String localidad;

    private LocalDateTime fechaRegistro = LocalDateTime.now();

    private Boolean activo;

    private Boolean admin;
    
}
