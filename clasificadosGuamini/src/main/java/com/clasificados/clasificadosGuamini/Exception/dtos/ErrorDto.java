package com.clasificados.clasificadosGuamini.exception.dtos;

import java.time.LocalDateTime;

public class ErrorDto {
    
    private String nombre;
    private String status;
    private LocalDateTime fecha;

    public ErrorDto(String nombre, String status) {
        this.fecha = LocalDateTime.now();
        this.nombre = nombre;
        this.status = status;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
