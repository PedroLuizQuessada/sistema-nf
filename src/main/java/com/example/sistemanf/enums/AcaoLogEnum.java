package com.example.sistemanf.enums;

import lombok.Getter;

@Getter
public enum AcaoLogEnum {
    CANCELAR_SOLICITACAO("Atualizar status: CANCELADO"),
    ATUALIZAR_STATUS_SOLICITACAO("Atualizar status: %s");

    private final String acao;

    AcaoLogEnum(String acao) {
        this.acao = acao;
    }
}
