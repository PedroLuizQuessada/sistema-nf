package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.LoginDto;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.UsuarioMapper;
import com.example.sistemanf.usecases.LoginUseCase;

public class LoginController {

    private final UsuarioDataSource userDataSource;

    public LoginController(UsuarioDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    public LoginDto login(String login) {
        UsuarioGateway userGateway = new UsuarioGateway(this.userDataSource);
        LoginUseCase loginUseCase = new LoginUseCase(userGateway);
        Usuario user = loginUseCase.execute(login);
        return UsuarioMapper.getLoginDto(user);
    }
}
