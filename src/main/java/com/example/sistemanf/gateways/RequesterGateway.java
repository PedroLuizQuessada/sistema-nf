package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.entities.Requester;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.mappers.RequesterMapper;

public class RequesterGateway {

    private final RequesterDataSource requesterDataSource;

    public RequesterGateway(RequesterDataSource requesterDataSource) {
        this.requesterDataSource = requesterDataSource;
    }

    public Requester getRequester(TipoUsuarioEnum tipo, String email) {
        RequesterDto requesterDto = requesterDataSource.getRequester(tipo, email);
        return RequesterMapper.getEntidade(requesterDto);
    }
}
