package com.shop.tienda_ropa.infrastructure.adapter.in.web;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.shop.tienda_ropa.domain.exception.RecursoNoEncontradoException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class ManejadorExcepcionesWeb {

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> noEncontrado(RecursoNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).
        contentType(new MediaType(MediaType.TEXT_PLAIN, StandardCharsets.UTF_8)).body(e.getMessage());
    }
}
