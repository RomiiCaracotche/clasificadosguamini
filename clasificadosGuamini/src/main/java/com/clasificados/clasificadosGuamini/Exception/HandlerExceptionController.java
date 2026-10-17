package com.clasificados.clasificadosGuamini.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.clasificados.clasificadosGuamini.Exception.dtos.DuplicateResourceException;
import com.clasificados.clasificadosGuamini.Exception.dtos.ErrorDto;
import com.clasificados.clasificadosGuamini.Exception.dtos.ResourceNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.access.AccessDeniedException;

@RestControllerAdvice 
public class HandlerExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String mensajeDetallado = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorDto error = new ErrorDto(mensajeDetallado, "400");
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorDto> authenticationException(AuthenticationException ex) {
        ErrorDto error = new ErrorDto("No estas autenticado", "401");
        return ResponseEntity.status(403).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> accessDeniedException(AccessDeniedException ex) {
        ErrorDto error = new ErrorDto("No tienen permiso para realizar esta accion", "403");
        return ResponseEntity.status(403).body(error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> noHandlerFoundException(NoHandlerFoundException ex) {
        ErrorDto error = new ErrorDto("Recurso no encontrado", "404");
        return ResponseEntity.status(404).body(error);
    } 

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorDto error = new ErrorDto("Ocurrió un error interno en el servidor", "500");
        return ResponseEntity.status(500).body(error);
    }

    

    //PERSONALIZADAS

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDto> RersourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "404");
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorDto> duplicateResourceException(DuplicateResourceException ex) {
    ErrorDto error = new ErrorDto(ex.getMessage(), "409");  
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
