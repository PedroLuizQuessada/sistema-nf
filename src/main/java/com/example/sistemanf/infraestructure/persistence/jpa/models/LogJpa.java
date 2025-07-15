package com.example.sistemanf.infraestructure.persistence.jpa.models;

import com.example.sistemanf.infraestructure.exceptions.ValorInvalidoJpaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "logs")
@Getter
@NoArgsConstructor
public class LogJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitacao", referencedColumnName = "id", nullable = false)
    private SolicitacaoJpa solicitacao;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id", nullable = false)
    private UsuarioJpa usuario;

    @Column(nullable = false, name = "data_alteracao")
    private Date dataAlteracao;

    @Column(nullable = false)
    private String acao;

    public LogJpa(Long id, SolicitacaoJpa solicitacao, UsuarioJpa usuario, Date dataAlteracao, String acao) {
        validarSolicitacao(solicitacao);
        validarUsuario(usuario);
        validarDataAlteracao(dataAlteracao);
        validarAcao(acao);

        this.id = id;
        this.solicitacao = solicitacao;
        this.usuario = usuario;
        this.dataAlteracao = dataAlteracao;
        this.acao = acao;
    }

    private void validarSolicitacao(SolicitacaoJpa solicitacao) {
        if (Objects.isNull(solicitacao))
            throw new ValorInvalidoJpaException("o log deve possuir uma solicitacao.");
    }

    private void validarUsuario(UsuarioJpa usuario) {
        if (Objects.isNull(usuario))
            throw new ValorInvalidoJpaException("o log deve possuir um usuario.");
    }

    private void validarDataAlteracao(Date dataAlteracao) {
        if (Objects.isNull(dataAlteracao))
            throw new ValorInvalidoJpaException("o log deve possuir uma data de alteração.");
    }

    private void validarAcao(String acao) {
        if (Objects.isNull(acao))
            throw new ValorInvalidoJpaException("o log deve possuir uma ação.");

        if (acao.length() > 255)
            throw new ValorInvalidoJpaException("a ação do log deve possuir até 255 caracteres.");
    }
}
