package com.unifor.MedMaisFacil.exceptions;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildResponse (HttpStatus httpStatus, String message) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("status", httpStatus.value());
        body.put("error", httpStatus.getReasonPhrase());
        body.put("message", message);

        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler(PacienteNotFoundException.class)
    public ResponseEntity<Object> handlePacienteNotFoundException (PacienteNotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException () {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro interno no servidor.");
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<Object> handleInvalidDataAccessApiUsageException (InvalidDataAccessApiUsageException ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

}
