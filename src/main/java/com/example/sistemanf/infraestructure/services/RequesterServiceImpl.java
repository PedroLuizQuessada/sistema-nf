package com.example.sistemanf.infraestructure.services;

import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import org.springframework.stereotype.Service;

@Service
public class RequesterServiceImpl implements RequesterDataSource {

    @Override
    public RequesterDto getRequester(TipoUsuarioEnum tipo, String email) {
        return new RequesterDto(tipo, email);
    }
}
