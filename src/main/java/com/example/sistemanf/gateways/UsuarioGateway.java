package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.exceptions.UsuarioNotFoundException;
import com.example.sistemanf.mappers.UsuarioMapper;

import java.util.Optional;

public class UsuarioGateway {

    private final UsuarioDataSource usuarioDataSource;

    public UsuarioGateway(UsuarioDataSource usuarioDataSource) {
        this.usuarioDataSource = usuarioDataSource;
    }

    public Usuario findUserByEmail(String email) {
        Optional<UsuarioDto> usuarioDtoOptional = usuarioDataSource.findUserByEmail(email);

        if (usuarioDtoOptional.isEmpty())
            throw new UsuarioNotFoundException();

        return UsuarioMapper.getEntidade(usuarioDtoOptional.get(), false);
    }

    public Long countByNome(String nome) {
        return usuarioDataSource.countByNome(nome);
    }

    public Long countByEmail(String email) {
        return usuarioDataSource.countByEmail(email);
    }

    public Usuario criarUsuario(UsuarioDto criarUsuarioDto) {
        UsuarioDto usuarioDto = usuarioDataSource.criarUsuario(criarUsuarioDto);
        return UsuarioMapper.getEntidade(usuarioDto, false);
    }
}
