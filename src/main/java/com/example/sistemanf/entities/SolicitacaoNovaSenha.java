package com.example.sistemanf.entities;

import com.example.sistemanf.exceptions.SolicitacaoNovaSenhaException;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Getter
public class SolicitacaoNovaSenha {

    private final Long id;
    private final Usuario usuario;
    private final String codigo;
    private final LocalDateTime dataExpiracao;
    private final Boolean ativo;

    public SolicitacaoNovaSenha(Usuario usuario) {
        validarUsuario(usuario);

        this.id = null;
        this.usuario = usuario;
        LocalDateTime agora = LocalDateTime.now();
        this.codigo = String.valueOf(agora.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).substring(0, 6);
        this.dataExpiracao = agora.plusMinutes(30);
        this.ativo = true;
    }

    public SolicitacaoNovaSenha(Long id, Usuario usuario, String codigo, LocalDateTime dataExpiracao, Boolean ativo) {
        validarUsuario(usuario);
        validarCodigo(codigo);
        validarDataExpiracao(dataExpiracao);
        validarAtivo(ativo);

        this.id = id;
        this.usuario = usuario;
        this.codigo = codigo;
        this.dataExpiracao = dataExpiracao;
        this.ativo = ativo;
    }

    private void validarUsuario(Usuario usuario) {
        if (Objects.isNull(usuario))
            throw new SolicitacaoNovaSenhaException("Falha ao atribuir usuário para alteração de senha.");
    }

    private void validarCodigo(String codigo) {
        if (Objects.isNull(codigo) || codigo.length() != 6)
            throw new SolicitacaoNovaSenhaException("Falha ao gerar código para alteração de senha.");
    }

    private void validarDataExpiracao(LocalDateTime dataExpiracao) {
        if (Objects.isNull(dataExpiracao))
            throw new SolicitacaoNovaSenhaException("Falha ao atribuir data de expiração para alteração de senha.");
    }

    private void validarAtivo(Boolean ativo) {
        if (Objects.isNull(ativo))
            throw new SolicitacaoNovaSenhaException("Falha ao atribuir status de atividade para alteração de senha.");
    }
}
