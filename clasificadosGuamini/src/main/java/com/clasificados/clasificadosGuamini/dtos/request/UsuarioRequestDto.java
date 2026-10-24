package com.clasificados.clasificadosGuamini.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El numero de documento es obligatorio")
    private String dni;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min=5, max=12)
    private String nombreUsuario;

    @NotBlank(message = "El email es obligatorio")
    @Email 
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min=8, max=12)
    private String password;

    @NotBlank(message = "El numero de celular es obligatorio")
    private String celular;

    @NotBlank(message = "La localidad es obligatoria")
    private String localidad;

    private Boolean admin = false;


    public UsuarioRequestDto() {}

    public UsuarioRequestDto(String nombre, String apellido, String dni, String nombreUsuario, String email, String password, String celular, String localidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
