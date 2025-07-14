package com.example.sistemanf.exceptions;

public class GeracaoArquivoException extends RuntimeException {
    public GeracaoArquivoException(String message) {
        super("Falha ao gerar arquivo: " + message);
    }
}
