package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoDto;

public interface SolicitacaoDataSource {
    SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto);
}
