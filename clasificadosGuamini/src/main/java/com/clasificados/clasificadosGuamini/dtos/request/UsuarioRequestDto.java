package com.clasificados.clasificadosGuamini.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class UsuarioRequestDto {

    @NotBlank(message = "Este campo es obligatorio")
    private String nombre;

    @NotBlank(message = "Este campo es obligatorio")
    private String apellido;

    @NotBlank(message = "Este campo es obligatorio")
    private String nombreUsuario;

    @NotBlank(message = "Este campo es obligatorio")
    private String email;

    @NotBlank(message = "Este campo es obligatorio")
    private String password;

    @NotBlank(message = "Este campo es obligatorio")
    private String celular;

    @NotBlank(message = "Este campo es obligatorio")
    private String localidad;


    public UsuarioRequestDto() {}

    public UsuarioRequestDto(String nombre, String apellido, String nombreUsuario, String email, String password, String celular, String localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.celular = celular;
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

}
