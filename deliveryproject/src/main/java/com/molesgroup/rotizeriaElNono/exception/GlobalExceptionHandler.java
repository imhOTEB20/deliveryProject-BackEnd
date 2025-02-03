package com.molesgroup.rotizeriaElNono.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura excepciones de tipo ResourceNotFoundException y responde con HTTP 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleResourceNotFound(ResourceNotFoundException ex) {
        return Map.of("error", ex.getMessage());
    }

    //Captura excepciones de tipo UserTypeNotSupportedException y responde con HTTP 403
    @ExceptionHandler(UserTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, String> handleUserTypeNotSupported(UserTypeNotSupportedException ex) {
        return Map.of("error", ex.getMessage());
    }

    //Captura excepciones de tipo UserAlreadyExistsException
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return Map.of("error", ex.getMessage());
    }

    //Captura excepciones de tipo IllegalStateException
    //Principalmente si no si el casteo de Jwt produce un ClassCastException
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleIllegalStateException(IllegalStateException ex) {
        return Map.of("error", ex.getMessage());
    }

    //Manejo genérico de cualquier otra excepción no controlada con HTTP 500
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleGeneralException(Exception ex) {
        return Map.of("error", "An unexpected error occurred: " + ex.getMessage());
    }
}
