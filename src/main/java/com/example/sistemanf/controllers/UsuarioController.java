package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.EmpresaDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
import com.example.sistemanf.entities.Token;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.EmpresaGateway;
import com.example.sistemanf.gateways.TokenGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.TokenMapper;
import com.example.sistemanf.mappers.UsuarioMapper;
import com.example.sistemanf.usecases.CriarUsuarioFuncionarioUseCase;
import com.example.sistemanf.usecases.GerarTokenUseCase;

public class UsuarioController {

    private final UsuarioDataSource usuarioDataSource;
    private final EmpresaDataSource empresaDataSource;
    private final TokenDataSource tokenDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, EmpresaDataSource empresaDataSource, TokenDataSource tokenDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.empresaDataSource = empresaDataSource;
        this.tokenDataSource = tokenDataSource;
    }

    public UsuarioDto criarUsuarioFuncionario(CriarUsuarioFuncionarioRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        EmpresaGateway empresaGateway = new EmpresaGateway(empresaDataSource);
        CriarUsuarioFuncionarioUseCase useCase = new CriarUsuarioFuncionarioUseCase(usuarioGateway, empresaGateway);
        return UsuarioMapper.getDto(useCase.execute(request));
    }

    public TokenDto gerarToken(TipoUsuarioEnum tipo, String email) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GerarTokenUseCase useCase = new GerarTokenUseCase(tokenGateway);
        Token token = useCase.execute(tipo, email);
        return TokenMapper.getDto(token);
    }
}
