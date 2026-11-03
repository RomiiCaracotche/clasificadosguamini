package com.clasificados.clasificadosGuamini.exception.dtos;

import java.time.LocalDateTime;

public class ErrorDto {
    
    private String mensaje;
    private String status;
    private LocalDateTime fecha;

    public ErrorDto(String mensaje, String status) {
        this.fecha = LocalDateTime.now();
        this.mensaje = mensaje;
        this.status = status;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
