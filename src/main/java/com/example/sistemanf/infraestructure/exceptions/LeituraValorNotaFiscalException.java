package com.example.sistemanf.infraestructure.exceptions;

public class LeituraValorNotaFiscalException extends RuntimeException {
    public LeituraValorNotaFiscalException(String message) {
        super("Falha na leitura da nota fiscal: " + message);
    }
}
