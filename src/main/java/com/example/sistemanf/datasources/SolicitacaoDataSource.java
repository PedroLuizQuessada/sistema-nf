package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoDto;

import java.util.Optional;

public interface SolicitacaoDataSource {
    SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto);
    Optional<SolicitacaoDto> findSolicitacaoByIdAndEmailFuncionario(Long id, String email);
    Optional<SolicitacaoDto> findSolicitacaoById(Long id);
    void cancelarSolicitacao(SolicitacaoDto solicitacaoDto);
    SolicitacaoDto atualizarSolicitacao(SolicitacaoDto solicitacaoDto);
}
