package com.example.sistemanf.exceptions;

public class SolicitacaoNotFoundException extends RuntimeException {
    public SolicitacaoNotFoundException() {
        super("Solicitação não encontrada");
    }
}
