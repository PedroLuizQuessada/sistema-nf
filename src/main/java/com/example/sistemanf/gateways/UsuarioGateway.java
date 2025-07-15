package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.exceptions.UsuarioNotFoundException;
import com.example.sistemanf.mappers.UsuarioMapper;

import java.util.Objects;
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

    public Usuario findUserAtivoByEmail(String email) {
        Optional<UsuarioDto> usuarioDtoOptional = usuarioDataSource.findUserAtivoByEmail(email);

        if (usuarioDtoOptional.isEmpty())
            throw new UsuarioNotFoundException();

        return UsuarioMapper.getEntidade(usuarioDtoOptional.get(), false);
    }

    public Usuario findUserById(Long id) {
        if (Objects.isNull(id))
            throw new UsuarioNotFoundException();

        Optional<UsuarioDto> optionalUsuarioDto = usuarioDataSource.findUserById(id);

        if (optionalUsuarioDto.isEmpty())
            throw new UsuarioNotFoundException();

        return UsuarioMapper.getEntidade(optionalUsuarioDto.get(), false);
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

    public void excluir(UsuarioDto usuarioDto) {
        usuarioDataSource.excluirUsuario(usuarioDto);
    }
}
