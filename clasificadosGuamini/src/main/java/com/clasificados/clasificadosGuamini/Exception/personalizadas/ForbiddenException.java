package com.clasificados.clasificadosGuamini.exception.personalizadas;

public class ForbiddenException extends RuntimeException {
    
    public ForbiddenException(String mensaje) {
        super(mensaje);
    }
}
