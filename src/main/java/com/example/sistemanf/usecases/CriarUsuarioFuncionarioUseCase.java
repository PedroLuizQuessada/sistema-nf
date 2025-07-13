package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.exceptions.ValorInvalidoException;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.UsuarioMapper;

public class CriarUsuarioFuncionarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public CriarUsuarioFuncionarioUseCase(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    public Usuario execute(CriarUsuarioFuncionarioRequest request, String requesterEmail) {
        Usuario requester = usuarioGateway.findUserByEmail(requesterEmail);
        Usuario usuario = UsuarioMapper.getEntidade(request, requester.getEmpresa(), TipoUsuarioEnum.FUNCIONARIO);
        checkNomeDisponivel(usuario.getNome());
        checkEmailDisponivel(usuario.getEmail());
        return usuarioGateway.criarUsuario(UsuarioMapper.getDto(usuario));
    }

    private void checkNomeDisponivel(String nome) {
        if (usuarioGateway.countByNome(nome) > 0) {
            throw new ValorInvalidoException("nome já está sendo usado.");
        }
    }

    private void checkEmailDisponivel(String email) {
        if (usuarioGateway.countByEmail(email) > 0) {
            throw new ValorInvalidoException("e-mail já está sendo usado.");
        }
    }
}
