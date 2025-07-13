package com.example.sistemanf.infraestructure.services;

import com.example.sistemanf.controllers.LoginController;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.LoginDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final LoginController loginController;

    public UserDetailsServiceImpl(UsuarioDataSource usuarioDataSource) {
        this.loginController = new LoginController(usuarioDataSource);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        LoginDto login = loginController.login(username);
        return new org.springframework.security.core.userdetails.User(login.email(), login.senha(),
                List.of(new SimpleGrantedAuthority(String.valueOf(login.tipo()))));
    }
}
