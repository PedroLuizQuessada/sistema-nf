package com.example.sistemanf.infraestructure.exceptions;

public class ValorInvalidoJpaException extends RuntimeException {
    public ValorInvalidoJpaException(String message) {
        super("Valor inválido para ser armazenado: " + message);
    }
}
