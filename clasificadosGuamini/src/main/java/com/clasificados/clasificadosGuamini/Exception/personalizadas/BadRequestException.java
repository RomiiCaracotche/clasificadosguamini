package com.clasificados.clasificadosGuamini.exception.personalizadas;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
