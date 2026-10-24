package com.clasificados.clasificadosGuamini.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequestDto {
    
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min=5, max=12)
    private String nombreUsuario;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=8, max=12)  
    private String password;

    public LoginRequestDto() {}

    public LoginRequestDto(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    } 

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    } 
}
