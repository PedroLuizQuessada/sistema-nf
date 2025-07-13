package com.example.sistemanf.exceptions;

public class ValorInvalidoException extends RuntimeException {
    public ValorInvalidoException(String message) {
        super("Valor inválido: " + message);
    }
}
