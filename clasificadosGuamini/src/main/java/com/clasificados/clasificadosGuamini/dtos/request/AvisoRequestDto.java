package com.clasificados.clasificadosGuamini.dtos.request;

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
    private Long idAnunciante;

    
    public AvisoRequestDto() {}

    public AvisoRequestDto(String titulo, String descripcion, String localidad, Long idAnunciante) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.localidad = localidad;
        this.idAnunciante = idAnunciante;
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

    public Long getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(Long idAnunciante) {
        this.idAnunciante = idAnunciante;
    }

}
