package com.br.joaoptgaino.jwtauthlearning.infra.exceptions.handler;


import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.joaoptgaino.jwtauthlearning.infra.exceptions.UserNotFoundException;
import com.br.joaoptgaino.jwtauthlearning.infra.exceptions.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "").toList();
        var errorResponse = ApiError.builder()
                .errorCode(ex.getStatusCode().toString())
                .message(ex.getMessage())
                .errors(errors)
                .build();
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(errorResponse);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<ApiError> handleJwtVerificationException(JWTVerificationException ex) {
        var errorResponse = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .errorCode("401")
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(401)
                .body(errorResponse);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException ex) {
        var errorResponse = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .errorCode(HttpStatus.NOT_FOUND.toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
