package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Requester;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.RequesterGateway;

public class GetRequesterByTipoAndEmailUseCase {

    private final RequesterGateway requesterGateway;

    public GetRequesterByTipoAndEmailUseCase(RequesterGateway requesterGateway) {
        this.requesterGateway = requesterGateway;
    }

    public Requester execute(TipoUsuarioEnum tipo, String email) {
        return requesterGateway.getRequester(tipo, email);
    }
}
