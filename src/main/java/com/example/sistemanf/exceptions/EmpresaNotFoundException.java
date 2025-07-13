package com.example.sistemanf.exceptions;

public class EmpresaNotFoundException extends RuntimeException {
    public EmpresaNotFoundException() {
        super("Empresa não encontrada");
    }
}
