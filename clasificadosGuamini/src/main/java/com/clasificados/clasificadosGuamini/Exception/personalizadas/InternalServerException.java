package com.clasificados.clasificadosGuamini.exception.personalizadas;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
