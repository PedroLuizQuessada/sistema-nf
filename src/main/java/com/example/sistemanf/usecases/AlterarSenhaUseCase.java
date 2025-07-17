package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.requests.AlterarSenhaRequest;
import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.gateways.SolicitacaoNovaSenhaGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.UsuarioMapper;

public class AlterarSenhaUseCase {

    private final SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway;
    private final UsuarioGateway usuarioGateway;

    public AlterarSenhaUseCase(SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoNovaSenhaGateway = solicitacaoNovaSenhaGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(AlterarSenhaRequest request, String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        SolicitacaoNovaSenha solicitacaoNovaSenha = solicitacaoNovaSenhaGateway.findSolicitacaoNovaSenhaAtivaByCodigoAndUsuarioId(request.codigo(), usuario.getId());
        usuario.setSenha(request.novaSenha());
        usuarioGateway.atualizarUsuario(UsuarioMapper.getDto(usuario));
        solicitacaoNovaSenhaGateway.consumirSolicitacaoNovaSenha(solicitacaoNovaSenha.getId());
    }
}
