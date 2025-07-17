package com.example.sistemanf.infraestructure.persistence.jpa.models;

import com.example.sistemanf.infraestructure.exceptions.ValorInvalidoJpaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "solicitacoes_nova_senha")
@Getter
@NoArgsConstructor
public class SolicitacaoNovaSenhaJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    private UsuarioJpa usuario;

    @Column(nullable = false, length = 6)
    private String codigo;

    @Column(name = "data_expiracao", nullable = false)
    private LocalDateTime dataExpiracao;

    @Column(nullable = false)
    private Boolean ativo;

    public SolicitacaoNovaSenhaJpa(Long id, UsuarioJpa usuario, String codigo, LocalDateTime dataExpiracao, Boolean ativo) {
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

    private void validarUsuario(UsuarioJpa usuario) {
        if (Objects.isNull(usuario))
            throw new ValorInvalidoJpaException("a solicitação para nova senha deve possuir um usuario.");
    }

    private void validarCodigo(String codigo) {
        if (Objects.isNull(codigo))
            throw new ValorInvalidoJpaException("a solicitação para nova senha deve possuir um código.");

        if (codigo.length() > 6)
            throw new ValorInvalidoJpaException("o código da solicitação para nova senha deve possuir até 6 caracteres.");
    }

    private void validarDataExpiracao(LocalDateTime dataExpiracao) {
        if (Objects.isNull(dataExpiracao))
            throw new ValorInvalidoJpaException("a solicitação para nova senha deve possuir uma data de expiração.");
    }

    private void validarAtivo(Boolean ativo) {
        if (Objects.isNull(ativo))
            throw new ValorInvalidoJpaException("a solicitação para nova senha deve possuir um indicativo de atividade.");
    }
}
