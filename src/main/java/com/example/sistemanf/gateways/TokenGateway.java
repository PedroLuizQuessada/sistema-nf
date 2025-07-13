package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.entities.Requester;
import com.example.sistemanf.entities.Token;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.mappers.RequesterMapper;
import com.example.sistemanf.mappers.TokenMapper;

public class TokenGateway {

    private final TokenDataSource tokenDataSource;

    public TokenGateway(TokenDataSource tokenDataSource) {
        this.tokenDataSource = tokenDataSource;
    }

    public Token gerarToken(TipoUsuarioEnum tipo, String email) {
        TokenDto tokenDto = tokenDataSource.gerarToken(tipo, email);
        return TokenMapper.getEntidade(tokenDto);
    }

    public Requester getRequester(String token) {
        RequesterDto requesterDto = tokenDataSource.getRequester(token);
        return RequesterMapper.getEntidade(requesterDto);
    }
}
