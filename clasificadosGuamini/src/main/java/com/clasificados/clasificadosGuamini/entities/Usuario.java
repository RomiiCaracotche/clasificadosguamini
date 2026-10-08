package com.clasificados.clasificadosGuamini.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity 
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String apellido;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(name="nombre_usuario", nullable = false)
    private String nombreUsuario;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String email;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String celular;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String localidad;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private Boolean activo = true;

    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name="avisos_favoritos",
        joinColumns=@JoinColumn(name="usuario_id"),
        inverseJoinColumns=@JoinColumn(name="aviso_id")
    )
    private Set<Aviso> favoritos = new HashSet<>();

    @OneToMany (mappedBy = "anunciante", orphanRemoval = true)
    private List<Aviso> avisos = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nombre, String apellido, String nombreUsuario, String email, String password, String celular, String localidad, LocalDateTime fechaRegistro, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.celular = celular;
        this.localidad = localidad;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
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

    public List<Aviso> getAvisos() {
        return avisos;
    }

    public void setAvisos(List<Aviso> avisos) {
        this.avisos = avisos;
    }

    public Set<Aviso> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(Set<Aviso> favoritos) {
        this.favoritos = favoritos;
    }
    
}
