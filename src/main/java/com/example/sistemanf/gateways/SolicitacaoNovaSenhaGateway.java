package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.SolicitacaoNovaSenhaDataSource;
import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.mappers.SolicitacaoNovaSenhaMapper;

public class SolicitacaoNovaSenhaGateway {

    private final SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource;

    public SolicitacaoNovaSenhaGateway(SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource) {
        this.solicitacaoNovaSenhaDataSource = solicitacaoNovaSenhaDataSource;
    }

    public SolicitacaoNovaSenha criarSolicitacaoNovaSenha(SolicitacaoNovaSenhaDto criarSolicitacaoNovaSenhaDto) {
        SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto = solicitacaoNovaSenhaDataSource.criarSolicitacaoNovaSenha(criarSolicitacaoNovaSenhaDto);
        return SolicitacaoNovaSenhaMapper.getEntidade(solicitacaoNovaSenhaDto);
    }
}
