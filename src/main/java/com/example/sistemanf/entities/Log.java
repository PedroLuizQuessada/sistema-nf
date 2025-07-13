package com.example.sistemanf.entities;

import com.example.sistemanf.exceptions.ValorInvalidoException;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class Log {

    private final Long id;
    private final Solicitacao solicitacao;
    private final Usuario usuario;
    private final Date dataAlteracao;
    private final String acao;

    public Log(Long id, Solicitacao solicitacao, Usuario usuario, Date dataAlteracao, String acao) {
        validarSolicitacao(solicitacao);
        validarUsuario(usuario);
        validarAcao(acao);

        this.id = id;
        this.solicitacao = solicitacao;
        this.usuario = usuario;
        this.acao = acao;

        this.dataAlteracao = !Objects.isNull(dataAlteracao) ? dataAlteracao : new java.sql.Date(java.sql.Date.from(Instant.now()).getTime());
    }

    private void validarSolicitacao(Solicitacao solicitacao) {
        if (Objects.isNull(solicitacao))
            throw new ValorInvalidoException("log deve possuir uma solicitação");
    }

    private void validarUsuario(Usuario usuario) {
        if (Objects.isNull(usuario))
            throw new ValorInvalidoException("log deve possuir um usuário");
    }

    private void validarAcao(String acao) {
        if (Objects.isNull(acao))
            throw new ValorInvalidoException("log deve possuir uma ação");
    }
}
