package com.clasificados.clasificadosGuamini.dtos.request;

public class LoginRequestDto {
    
    private String nombreUsuario;   
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
