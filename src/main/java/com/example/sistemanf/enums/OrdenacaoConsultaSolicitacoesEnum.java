package com.example.sistemanf.enums;

import lombok.Getter;

@Getter
public enum OrdenacaoConsultaSolicitacoesEnum {
    ID("id"),
    STATUS("status"),
    DATA_EMISSAO("dataEmissao"),
    DATA_UPLOAD("dataUpload"),
    VALOR("valor"),
    NOME_FUNCIONARIO("funcionario.nome"),
    DESCRICAO("descricao"),
    CNPJ_SERVICO("cnpjServico");

    private final String campo;

    OrdenacaoConsultaSolicitacoesEnum(String campo) {
        this.campo = campo;
    }
}
