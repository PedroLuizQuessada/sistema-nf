package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.entities.SolicitacaoNovaSenha;
import com.example.sistemanf.entities.Usuario;

import java.util.Objects;

public class SolicitacaoNovaSenhaMapper {

    private SolicitacaoNovaSenhaMapper() {}

    public static SolicitacaoNovaSenha getEntidade(Usuario usuario) {
        return new SolicitacaoNovaSenha(usuario);
    }

    public static SolicitacaoNovaSenha getEntidade(SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto) {
        return new SolicitacaoNovaSenha(solicitacaoNovaSenhaDto.id(),
                !Objects.isNull(solicitacaoNovaSenhaDto.usuarioDto()) ? UsuarioMapper.getEntidade(solicitacaoNovaSenhaDto.usuarioDto(), false) : null,
                solicitacaoNovaSenhaDto.codigo(), solicitacaoNovaSenhaDto.dataExpiracao());
    }

    public static SolicitacaoNovaSenhaDto getDto(SolicitacaoNovaSenha solicitacaoNovaSenha) {
        return new SolicitacaoNovaSenhaDto(solicitacaoNovaSenha.getId(),
                !Objects.isNull(solicitacaoNovaSenha.getUsuario()) ? UsuarioMapper.getDto(solicitacaoNovaSenha.getUsuario()) : null,
                solicitacaoNovaSenha.getCodigo(), solicitacaoNovaSenha.getDataExpiracao());
    }

    public static SolicitacaoNovaSenhaDto getResponse(SolicitacaoNovaSenha solicitacaoNovaSenha) {
        return new SolicitacaoNovaSenhaDto(solicitacaoNovaSenha.getId(),
                !Objects.isNull(solicitacaoNovaSenha.getUsuario()) ? UsuarioMapper.getResponse(solicitacaoNovaSenha.getUsuario()) : null,
                null, solicitacaoNovaSenha.getDataExpiracao());
    }
}
