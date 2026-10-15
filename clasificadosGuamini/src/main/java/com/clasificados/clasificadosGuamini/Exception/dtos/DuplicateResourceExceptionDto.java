package com.clasificados.clasificadosGuamini.Exception.dtos;

public class DuplicateResourceExceptionDto extends RuntimeException {
    
    public DuplicateResourceExceptionDto(String message) {
        super(message);
    }

}
