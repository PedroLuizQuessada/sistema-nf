package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;

import java.util.Optional;

public interface SolicitacaoNovaSenhaDataSource {
    SolicitacaoNovaSenhaDto criarSolicitacaoNovaSenha(SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto);
    Optional<SolicitacaoNovaSenhaDto> findSolicitacaoNovaSenhaAtivaByCodigoAndUsuarioId(String codigo, Long usuarioId);
    void consumirSolicitacaoNovaSenha(Long id);
}
