package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.entities.Requester;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.RequesterGateway;
import com.example.sistemanf.gateways.TokenGateway;
import com.example.sistemanf.mappers.RequesterMapper;
import com.example.sistemanf.usecases.GetRequesterByTipoAndEmailUseCase;
import com.example.sistemanf.usecases.GetRequesterByTokenUseCase;

public class RequesterController {

    private final RequesterDataSource requesterDataSource;
    private final TokenDataSource tokenDataSource;

    public RequesterController(RequesterDataSource requesterDataSource, TokenDataSource tokenDataSource) {
        this.requesterDataSource = requesterDataSource;
        this.tokenDataSource = tokenDataSource;
    }

    public RequesterDto getRequester(TipoUsuarioEnum tipo, String email) {
        RequesterGateway requesterGateway = new RequesterGateway(requesterDataSource);
        GetRequesterByTipoAndEmailUseCase getRequesterUseCase = new GetRequesterByTipoAndEmailUseCase(requesterGateway);
        Requester requester = getRequesterUseCase.execute(tipo, email);
        return RequesterMapper.getDto(requester);
    }

    public RequesterDto getRequester(String token) {
        TokenGateway tokenGateway = new TokenGateway(tokenDataSource);
        GetRequesterByTokenUseCase getRequesterUseCase = new GetRequesterByTokenUseCase(tokenGateway);
        Requester requester = getRequesterUseCase.execute(token);
        return RequesterMapper.getDto(requester);
    }
}
