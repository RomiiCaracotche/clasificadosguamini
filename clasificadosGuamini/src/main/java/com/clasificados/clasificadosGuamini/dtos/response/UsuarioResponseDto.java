package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;

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

    private boolean admin;


    public UsuarioResponseDto() {}

    public UsuarioResponseDto(Long id, String nombre, String apellido, String dni, String nombreUsuario, String email, String celular, String localidad, LocalDateTime fechaRegistro, Boolean activo, boolean admin) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.celular = celular;
        this.localidad = localidad;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
}
