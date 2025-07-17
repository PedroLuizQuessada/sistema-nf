package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.SolicitacaoNovaSenhaDataSource;
import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.exceptions.SolicitacaoNovaSenhaNotFoundException;
import com.example.sistemanf.mappers.SolicitacaoNovaSenhaMapper;

import java.util.Optional;

public class SolicitacaoNovaSenhaGateway {

    private final SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource;

    public SolicitacaoNovaSenhaGateway(SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource) {
        this.solicitacaoNovaSenhaDataSource = solicitacaoNovaSenhaDataSource;
    }

    public SolicitacaoNovaSenha criarSolicitacaoNovaSenha(SolicitacaoNovaSenhaDto criarSolicitacaoNovaSenhaDto) {
        SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto = solicitacaoNovaSenhaDataSource.criarSolicitacaoNovaSenha(criarSolicitacaoNovaSenhaDto);
        return SolicitacaoNovaSenhaMapper.getEntidade(solicitacaoNovaSenhaDto);
    }

    public SolicitacaoNovaSenha findSolicitacaoNovaSenhaAtivaByCodigoAndUsuarioId(String codigo, Long usuarioId) {
        Optional<SolicitacaoNovaSenhaDto> solicitacaoNovaSenhaDtoOptional = solicitacaoNovaSenhaDataSource.findSolicitacaoNovaSenhaAtivaByCodigoAndUsuarioId(codigo, usuarioId);

        if (solicitacaoNovaSenhaDtoOptional.isEmpty())
            throw new SolicitacaoNovaSenhaNotFoundException();

        return SolicitacaoNovaSenhaMapper.getEntidade(solicitacaoNovaSenhaDtoOptional.get());
    }

    public void consumirSolicitacaoNovaSenha(Long id) {
        solicitacaoNovaSenhaDataSource.consumirSolicitacaoNovaSenha(id);
    }
}
