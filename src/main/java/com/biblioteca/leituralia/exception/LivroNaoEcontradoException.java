package com.biblioteca.leituralia.exception;

public class LivroNaoEcontradoException extends RuntimeException {
    public LivroNaoEcontradoException(String message) {
        super(message);
    }
}
