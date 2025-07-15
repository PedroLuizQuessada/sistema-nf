package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Log;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.AcaoLogEnum;
import com.example.sistemanf.gateways.LogGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.LogMapper;
import com.example.sistemanf.mappers.SolicitacaoMapper;

public class CancelarSolicitacaoUseCase {

    private final SolicitacaoGateway solicitacaoGateway;
    private final UsuarioGateway usuarioGateway;
    private final LogGateway logGateway;

    public CancelarSolicitacaoUseCase(SolicitacaoGateway solicitacaoGateway, UsuarioGateway usuarioGateway, LogGateway logGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.usuarioGateway = usuarioGateway;
        this.logGateway = logGateway;
    }

    public void execute(Long id, String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        Solicitacao solicitacao = solicitacaoGateway.findSolicitacaoByIdAndEmailFuncionario(id, requesterEmail);
        solicitacaoGateway.cancelarSolicitacao(SolicitacaoMapper.getDto(solicitacao));
        Log log = LogMapper.getEntidade(solicitacao, usuario, AcaoLogEnum.CANCELAR_SOLICITACAO.getAcao());
        logGateway.criarLog(LogMapper.getDto(log));
    }
}
