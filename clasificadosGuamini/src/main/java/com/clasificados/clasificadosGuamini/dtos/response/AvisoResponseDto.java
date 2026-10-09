package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;

public class AvisoResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();
    private String localidad;
    private Long idAnunciante;

    public AvisoResponseDto(){}
    
    public AvisoResponseDto(Long id, String titulo, String descripcion, LocalDateTime fecha, String localidad, Long idAnunciante) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.localidad = localidad;
        this.idAnunciante = idAnunciante;
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

    public Long getIdAnunciante() {
        return idAnunciante;
    }

    public void setIdAnunciante(Long idAnunciante) {
        this.idAnunciante = idAnunciante;
    }
    
}
