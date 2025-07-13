package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Token;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.TokenGateway;

public class GerarTokenUseCase {

    private final TokenGateway tokenGateway;

    public GerarTokenUseCase(TokenGateway tokenGateway) {
        this.tokenGateway = tokenGateway;
    }

    public Token execute(TipoUsuarioEnum tipo, String email) {
        return  tokenGateway.gerarToken(tipo, email);
    }
}
