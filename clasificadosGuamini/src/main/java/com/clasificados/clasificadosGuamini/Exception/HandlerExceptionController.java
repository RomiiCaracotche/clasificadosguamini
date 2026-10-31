package com.clasificados.clasificadosGuamini.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.clasificados.clasificadosGuamini.exception.dtos.ErrorDto;
import com.clasificados.clasificadosGuamini.exception.personalizadas.BadRequestException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.DuplicateResourceException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ForbiddenException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.InternalServerException;
import com.clasificados.clasificadosGuamini.exception.personalizadas.ResourceNotFoundException;
import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice 
public class HandlerExceptionController {
/* 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String mensajeDetallado = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorDto error = new ErrorDto(mensajeDetallado, "400");
        return ResponseEntity.status(400).body(error);
    } */

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> authenticationException(AuthenticationException ex) {
        ErrorDto error = new ErrorDto("No estas autenticado", "401");
        return ResponseEntity.status(403).body(error);
    }

   /*  @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> accessDeniedException(AccessDeniedException ex) {
        ErrorDto error = new ErrorDto("No tienen permiso para realizar esta accion", "403");
        return ResponseEntity.status(403).body(error);
    } */
 

    //PERSONALIZADAS

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDto> badRequestException(BadRequestException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "400");
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorDto> accessDeniedException(ForbiddenException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "403");
        return ResponseEntity.status(403).body(error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> rersourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "404");
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDto> duplicateResourceException(DuplicateResourceException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "409");  
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler({Exception.class, InternalServerException.class})
    public ResponseEntity<ErrorDto> internalServerException(Exception ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "500"); 
        return ResponseEntity.status(500).body(error);
    }

}
