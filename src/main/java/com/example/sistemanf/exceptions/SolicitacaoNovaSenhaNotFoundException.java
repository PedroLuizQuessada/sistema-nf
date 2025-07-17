package com.example.sistemanf.exceptions;

public class SolicitacaoNovaSenhaNotFoundException extends RuntimeException {
    public SolicitacaoNovaSenhaNotFoundException() {
        super("Solicitação para alteração de senha não encontrada");
    }
}
