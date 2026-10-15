package com.clasificados.clasificadosGuamini.Exception.dtos;

public class ResourceNotFoundExceptionDto extends RuntimeException {
    
    public ResourceNotFoundExceptionDto(String message) {
        super(message);
    }

    //Para usarlo
    /* .orElseThrow(() -> new ResourceNotFoundException("No se encontró el usuario con el ID proporcionado")); */
}
