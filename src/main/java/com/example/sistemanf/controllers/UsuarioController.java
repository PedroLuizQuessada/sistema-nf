package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.EmailDataSource;
import com.example.sistemanf.datasources.SolicitacaoNovaSenhaDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.AlterarSenhaRequest;
import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.entities.Token;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.EmailGateway;
import com.example.sistemanf.gateways.SolicitacaoNovaSenhaGateway;
import com.example.sistemanf.gateways.TokenGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoNovaSenhaMapper;
import com.example.sistemanf.mappers.TokenMapper;
import com.example.sistemanf.mappers.UsuarioMapper;
import com.example.sistemanf.usecases.*;

public class UsuarioController {

    private final UsuarioDataSource usuarioDataSource;
    private final TokenDataSource tokenDataSource;
    private final SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource;
    private final EmailDataSource emailDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, TokenDataSource tokenDataSource, SolicitacaoNovaSenhaDataSource solicitacaoNovaSenhaDataSource,
                             EmailDataSource emailDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.tokenDataSource = tokenDataSource;
        this.solicitacaoNovaSenhaDataSource = solicitacaoNovaSenhaDataSource;
        this.emailDataSource = emailDataSource;
    }

    public UsuarioDto criarUsuarioFuncionario(CriarUsuarioFuncionarioRequest request, String requesterEmail) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        CriarUsuarioFuncionarioUseCase useCase = new CriarUsuarioFuncionarioUseCase(usuarioGateway);
        return UsuarioMapper.getResponse(useCase.execute(request, requesterEmail));
    }

    public TokenDto gerarToken(TipoUsuarioEnum tipo, String email) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GerarTokenUseCase useCase = new GerarTokenUseCase(tokenGateway);
        Token token = useCase.execute(tipo, email);
        return TokenMapper.getDto(token);
    }

    public void excluirUsuario(Long id, String requesterEmail) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ExcluirUsuarioUseCase useCase = new ExcluirUsuarioUseCase(usuarioGateway);
        useCase.execute(id, requesterEmail);
    }

    public SolicitacaoNovaSenhaDto gerarSolicitacaoNovaSenha(String requesterEmail) {
        SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway = new SolicitacaoNovaSenhaGateway(solicitacaoNovaSenhaDataSource);
        EmailGateway emailGateway = new EmailGateway(emailDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        GerarSolicitacaoNovaSenhaUseCase useCase = new GerarSolicitacaoNovaSenhaUseCase(solicitacaoNovaSenhaGateway, emailGateway, usuarioGateway);
        SolicitacaoNovaSenha solicitacaoNovaSenha = useCase.execute(requesterEmail);
        return SolicitacaoNovaSenhaMapper.getResponse(solicitacaoNovaSenha);
    }

    public void alterarSenha(AlterarSenhaRequest request, String requesterEmail) {
        SolicitacaoNovaSenhaGateway solicitacaoNovaSenhaGateway = new SolicitacaoNovaSenhaGateway(solicitacaoNovaSenhaDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        AlterarSenhaUseCase alterarSenhaUseCase = new AlterarSenhaUseCase(solicitacaoNovaSenhaGateway, usuarioGateway);
        alterarSenhaUseCase.execute(request, requesterEmail);
    }
}
