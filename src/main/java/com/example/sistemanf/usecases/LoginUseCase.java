package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.gateways.UsuarioGateway;

public class LoginUseCase {

    private final UsuarioGateway usuarioGateway;

    public LoginUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario execute(String email) {
        return usuarioGateway.findUserAtivoByEmail(email);
    }
}
