package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.entities.Usuario;

public class AvisoResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();
    private String localidad;
    private Usuario anunciante;

    public AvisoResponseDto(){}
    
    public AvisoResponseDto(Long id, String titulo, String descripcion, LocalDateTime fecha, String localidad, Usuario anunciante) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
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
