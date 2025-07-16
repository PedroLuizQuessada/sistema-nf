package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.gateways.EmailGateway;
import com.example.sistemanf.gateways.SolicitacaoNovaSenhaGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoNovaSenhaMapper;

public class GerarSolicitacaoNovaSenhaUseCase {

    private final static String ASSUNTO_EMAIL_NOVA_SENHA = "Alteração de senha para o sistema de notas fiscais.";
    private final static String MENSAGEM_EMAIL_NOVA_SENHA = "Seu código para alteração de senha no sistema de notas fiscais é: %s";

    private final SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway;
    private final EmailGateway emailGateway;
    private final UsuarioGateway usuarioGateway;

    public GerarSolicitacaoNovaSenhaUseCase(SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway, EmailGateway emailGateway, UsuarioGateway usuarioGateway) {
        this.solicitacaoNovaSenhaGateway = solicitacaoNovaSenhaGateway;
        this.emailGateway = emailGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public SolicitacaoNovaSenha execute(String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        SolicitacaoNovaSenha solicitacaoNovaSenha = SolicitacaoNovaSenhaMapper.getEntidade(usuario);
        solicitacaoNovaSenha = solicitacaoNovaSenhaGateway.criarSolicitacaoNovaSenha(SolicitacaoNovaSenhaMapper.getDto(solicitacaoNovaSenha));
        emailGateway.enviarEmail(usuario.getEmail(), ASSUNTO_EMAIL_NOVA_SENHA, String.format(MENSAGEM_EMAIL_NOVA_SENHA, solicitacaoNovaSenha.getCodigo()));
        return solicitacaoNovaSenha;
    }
}
