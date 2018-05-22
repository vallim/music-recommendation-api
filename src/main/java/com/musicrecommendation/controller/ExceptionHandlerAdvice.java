package com.musicrecommendation.controller;

import com.musicrecommendation.model.MessageDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<?> handle(RuntimeException ex) {
        return ResponseEntity.badRequest()
            .body(new MessageDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<?> handleHttpClientException(HttpClientErrorException ex) {
        return new ResponseEntity<>(ex.getResponseBodyAsByteArray(), ex.getResponseHeaders(),
            ex.getStatusCode());
    }
}
