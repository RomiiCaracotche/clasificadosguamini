package com.clasificados.clasificadosGuamini.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.clasificados.clasificadosGuamini.Exception.dtos.DuplicateResourceExceptionDto;
import com.clasificados.clasificadosGuamini.Exception.dtos.ErrorDto;
import com.clasificados.clasificadosGuamini.Exception.dtos.ResourceNotFoundExceptionDto;

@RestControllerAdvice 
public class HandlerExceptionController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> noHandlerFoundException(NoHandlerFoundException ex) {
        ErrorDto error = new ErrorDto("Recurso no encontrado", "404");
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String mensajeDetallado = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorDto error = new ErrorDto(mensajeDetallado, "400");
        return ResponseEntity.status(400).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGeneralException(Exception ex) {
        ErrorDto error = new ErrorDto("Ocurrió un error interno en el servidor", "500");
        return ResponseEntity.status(500).body(error);
    }

    //PERSONALIZADAS

    @ExceptionHandler(ResourceNotFoundExceptionDto.class)
    public ResponseEntity<ErrorDto> handleNotFound(ResourceNotFoundExceptionDto ex) {
        ErrorDto error = new ErrorDto(ex.getMessage(), "404");
        return ResponseEntity.status(404).body(error);
    }

    @ExceptionHandler(DuplicateResourceExceptionDto.class)
    public ResponseEntity<ErrorDto> handleDuplicateResourceExceptionDto(DuplicateResourceExceptionDto ex) {
    ErrorDto error = new ErrorDto(ex.getMessage(), "409");  
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
