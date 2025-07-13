package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Requester;
import com.example.sistemanf.gateways.TokenGateway;

public class GetRequesterByTokenUseCase {

    private final TokenGateway tokenGateway;

    public GetRequesterByTokenUseCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public Requester execute(String token) {
        return tokenGateway.getRequester(token);
    }
}
