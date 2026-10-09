package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AvisoRequestDto {

    @NotBlank(message = "Este campo es obligatorio")
    private String titulo;

    @NotBlank(message = "Este campo es obligatorio")
    private String descripcion;

    @NotBlank(message = "Este campo es obligatorio")
    private String localidad;

    @NotNull(message = "Este campo es obligatorio")
    private Usuario anunciante;

    
    public AvisoRequestDto() {}

    public AvisoRequestDto(String titulo, String descripcion, String localidad, Usuario anunciante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.anunciante = anunciante;
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
