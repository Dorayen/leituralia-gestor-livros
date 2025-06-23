package com.biblioteca.leituralia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EditoraNaoEncontradaException extends RuntimeException {
    public EditoraNaoEncontradaException(String message) {
        super(message);
    }
}
