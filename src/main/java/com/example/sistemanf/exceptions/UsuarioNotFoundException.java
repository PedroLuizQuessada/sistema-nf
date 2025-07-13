package com.example.sistemanf.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
