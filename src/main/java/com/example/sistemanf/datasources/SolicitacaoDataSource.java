package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoDto;

import java.util.Optional;

public interface SolicitacaoDataSource {
    SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto);
    Optional<SolicitacaoDto> findSolicitacaoByIdAndEmailFuncionario(Long id, String email);
    void cancelarSolicitacao(SolicitacaoDto solicitacaoDto);
}
