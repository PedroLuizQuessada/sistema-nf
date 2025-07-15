package com.example.sistemanf.infraestructure.exceptions;

public class LeituraNotaFiscalException extends RuntimeException {
    public LeituraNotaFiscalException(String message) {
        super("Falha na leitura da nota fiscal: " + message);
    }
}
