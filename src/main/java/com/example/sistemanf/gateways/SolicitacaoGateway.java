package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.mappers.SolicitacaoMapper;

public class SolicitacaoGateway {

    private final SolicitacaoDataSource solicitacaoDataSource;

    public SolicitacaoGateway(SolicitacaoDataSource solicitacaoDataSource) {
        this.solicitacaoDataSource = solicitacaoDataSource;
    }

    public Long selectMaxId() {
        return solicitacaoDataSource.selectMaxId();
    }

    public Solicitacao criarSolicitacao(SolicitacaoDto criarSolicitacaoDto) {
        SolicitacaoDto solicitacaoDto = solicitacaoDataSource.criarSolicitacao(criarSolicitacaoDto);
        return SolicitacaoMapper.getEntidade(solicitacaoDto);
    }
}
