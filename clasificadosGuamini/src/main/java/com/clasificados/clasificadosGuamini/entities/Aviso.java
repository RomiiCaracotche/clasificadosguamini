package com.clasificados.clasificadosGuamini.entities;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity 
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aviso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fecha = LocalDateTime.now();

    @NotBlank(message = "Este campo es obligatorio")
    @Column(nullable = false)
    private String localidad;

    @NotNull(message = "Este campo es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario anunciante;

    public Aviso() {}

    public Aviso(String titulo, String descripcion, String localidad, Usuario anunciante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.anunciante = anunciante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

}
