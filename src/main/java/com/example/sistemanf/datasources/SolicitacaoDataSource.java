package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoDto;

public interface SolicitacaoDataSource {
    Long selectMaxId();
    SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto);
}
