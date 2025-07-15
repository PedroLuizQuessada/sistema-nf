package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;

public class CancelarSolicitacaoUseCase {

    private final SolicitacaoGateway solicitacaoGateway;

    public CancelarSolicitacaoUseCase(SolicitacaoGateway solicitacaoGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
    }

    public void execute(Long id, String requesterEmail) {
        Solicitacao solicitacao = solicitacaoGateway.findSolicitacaoByIdAndEmailFuncionario(id, requesterEmail);
        solicitacaoGateway.cancelarSolicitacao(SolicitacaoMapper.getDto(solicitacao));
    }
}
