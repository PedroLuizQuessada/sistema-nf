package com.example.sistemanf.entities;

import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.exceptions.ValorInvalidoException;
import lombok.Getter;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Getter
public class Usuario {

    private final Long id;
    private final Empresa empresa;
    private final String nome;
    private final TipoUsuarioEnum tipo;
    private final String email;
    private final String senha;
    private final Date dataCriacao;
    private final Boolean ativo;

    public Usuario(Long id, Empresa empresa, String nome, TipoUsuarioEnum tipo, String email, String senha,
                   Date dataCriacao, boolean criptografar, Boolean ativo) {
        validarEmpresa(empresa);
        validarNome(nome);
        validarTipo(tipo);
        validarEmail(email);
        validateSenha(senha);
        validarAtivo(ativo);

        this.id = id;
        this.empresa = empresa;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.ativo = ativo;

        this.dataCriacao = !Objects.isNull(dataCriacao) ? dataCriacao : new java.sql.Date(java.sql.Date.from(Instant.now()).getTime());

        if (criptografar) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.senha = encoder.encode(senha);
        }
        else {
            this.senha = senha;
        }
    }

    private void validarEmpresa(Empresa empresa) {
        if (Objects.isNull(empresa))
            throw new ValorInvalidoException("usuário deve possuir uma empresa.");
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome) || nome.isEmpty())
            throw new ValorInvalidoException("usuário deve possuir um nome.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new ValorInvalidoException("usuário deve possuir um tipo.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email) || email.isEmpty())
            throw new ValorInvalidoException("usuário deve possuir um e-mail.");

        if (!EmailValidator.getInstance().isValid(email))
            throw new ValorInvalidoException("e-mail inválido.");
    }

    private void validateSenha(String senha) {
        if (Objects.isNull(senha) || senha.isEmpty())
            throw new ValorInvalidoException("usuário deve possuir uma senha.");

        if (senha.length() < 6)
            throw new ValorInvalidoException("a senha do usuário deve possuir ao menos 6 caracteres");
    }

    private void validarAtivo(Boolean ativo) {
        if (Objects.isNull(ativo))
            throw new ValorInvalidoException("usuário deve possuir um indicativo de atividade.");
    }
}
