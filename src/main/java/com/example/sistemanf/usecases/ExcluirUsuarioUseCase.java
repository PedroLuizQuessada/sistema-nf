package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.exceptions.UsuarioNotFoundException;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.UsuarioMapper;

import java.util.Objects;

public class ExcluirUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public ExcluirUsuarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public void execute(Long id, String requesterEmail) {
        Usuario requester = usuarioGateway.findUserByEmail(requesterEmail);
        Usuario usuario = usuarioGateway.findUserById(id);
        if (!Objects.equals(usuario.getEmpresa().getId(), requester.getEmpresa().getId()))
            throw new UsuarioNotFoundException();
        usuarioGateway.excluir(UsuarioMapper.getDto(usuario));
    }
}
