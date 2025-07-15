package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.requests.AtualizarStatusSolicitacaoRequest;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;

public class AtualizarStatusSolicitacaoUseCase {

    private final SolicitacaoGateway solicitacaoGateway;

    public AtualizarStatusSolicitacaoUseCase(SolicitacaoGateway solicitacaoGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
    }

    public Solicitacao execute(AtualizarStatusSolicitacaoRequest request, Long id) {
        Solicitacao solicitacao = solicitacaoGateway.findSolicitacaoById(id);
        solicitacao.setStatus(request.status());
        return solicitacaoGateway.atualizarSolicitacao(SolicitacaoMapper.getDto(solicitacao));
    }
}
