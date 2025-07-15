package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.requests.AtualizarStatusSolicitacaoRequest;
import com.example.sistemanf.entities.Log;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.AcaoLogEnum;
import com.example.sistemanf.gateways.LogGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.LogMapper;
import com.example.sistemanf.mappers.SolicitacaoMapper;

public class AtualizarStatusSolicitacaoUseCase {

    private final SolicitacaoGateway solicitacaoGateway;
    private final UsuarioGateway usuarioGateway;
    private final LogGateway logGateway;

    public AtualizarStatusSolicitacaoUseCase(SolicitacaoGateway solicitacaoGateway, UsuarioGateway usuarioGateway, LogGateway logGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.usuarioGateway = usuarioGateway;
        this.logGateway = logGateway;
    }

    public Solicitacao execute(AtualizarStatusSolicitacaoRequest request, Long id, String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        Solicitacao solicitacao = solicitacaoGateway.findSolicitacaoById(id);
        solicitacao.setStatus(request.status());
        solicitacao = solicitacaoGateway.atualizarSolicitacao(SolicitacaoMapper.getDto(solicitacao));
        Log log = LogMapper.getEntidade(solicitacao, usuario, String.format(AcaoLogEnum.ATUALIZAR_STATUS_SOLICITACAO.getAcao(), request.status()));
        logGateway.criarLog(LogMapper.getDto(log));
        return solicitacao;
    }
}
