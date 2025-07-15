package com.example.sistemanf.infraestructure.persistence.jpa.models;

import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.infraestructure.exceptions.ValorInvalidoJpaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
public class UsuarioJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa", referencedColumnName = "id", nullable = false)
    private EmpresaJpa empresa;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuarioEnum tipo;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false, name = "data_criacao")
    private Date dataCriacao;

    @Column(nullable = false)
    private Boolean ativo;

    public UsuarioJpa(Long id, EmpresaJpa empresa, String nome, TipoUsuarioEnum tipo, String email, String senha, Date dataCriacao, Boolean ativo) {
        validarEmpresa(empresa);
        validarNome(nome);
        validarTipo(tipo);
        validarEmail(email);
        validarSenha(senha);
        validarDataCriacao(dataCriacao);
        validarAtivo(ativo);

        this.id = id;
        this.empresa = empresa;
        this.nome = nome;
        this.tipo = tipo;
        this.email = email;
        this.senha = senha;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
    }

    private void validarEmpresa(EmpresaJpa empresa) {
        if (Objects.isNull(empresa))
            throw new ValorInvalidoJpaException("o usuário deve possuir uma empresa.");
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome))
            throw new ValorInvalidoJpaException("o usuário deve possuir um nome.");

        if (nome.length() > 255)
            throw new ValorInvalidoJpaException("o nome do usuário deve possuir até 255 caracteres.");
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new ValorInvalidoJpaException("o usuário deve possuir um tipo.");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email))
            throw new ValorInvalidoJpaException("o usuário deve possuir um e-mail.");

        if (email.length() > 255)
            throw new ValorInvalidoJpaException("o e-mail do usuário deve possuir até 255 caracteres.");
    }

    private void validarSenha(String senha) {
        if (Objects.isNull(senha))
            throw new ValorInvalidoJpaException("o usuário deve possuir uma senha.");

        if (senha.length() > 255)
            throw new ValorInvalidoJpaException("senha criptografada inválida.");
    }

    private void validarDataCriacao(Date dataCriacao) {
        if (Objects.isNull(dataCriacao))
            throw new ValorInvalidoJpaException("o usuário deve possuir uma data de criação.");
    }

    private void validarAtivo(Boolean ativo) {
        if (Objects.isNull(ativo))
            throw new ValorInvalidoJpaException("o usuário deve possuir um indicativo de atividade.");
    }
}
