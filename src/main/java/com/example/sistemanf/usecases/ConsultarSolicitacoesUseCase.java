package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.requests.ConsultarSolicitacoesRequest;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;

import java.util.List;
import java.util.Objects;

public class ConsultarSolicitacoesUseCase {

    private final SolicitacaoGateway solicitacaoGateway;
    private final UsuarioGateway usuarioGateway;

    public ConsultarSolicitacoesUseCase(SolicitacaoGateway solicitacaoGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoGateway = solicitacaoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public List<Solicitacao> execute(ConsultarSolicitacoesRequest request, String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        String nomeFuncionario = request.nomeFuncionario();
        Long empresa = usuario.getEmpresa().getId();

        if (Objects.equals(usuario.getTipo(), TipoUsuarioEnum.FUNCIONARIO))
            nomeFuncionario = usuario.getNome();

        return solicitacaoGateway.consultarSolicitacoes(request.page(), request.size(), request.ordenacao(), request.sentidoOrdenacao(),
                empresa, request.id(), request.status(), request.dataEmissaoInicio(), request.dataEmissaoFim(), request.dataUploadInicio(),
                request.dataUploadFim(), request.valor(), nomeFuncionario, request.descricao(), request.cnpjServico());
    }
}
