package com.example.sistemanf.infraestructure.persistence.jpa.models;

import com.example.sistemanf.enums.StatusSolicitacaoEnum;
import com.example.sistemanf.infraestructure.exceptions.ValorInvalidoJpaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "solicitacoes")
@Getter
@NoArgsConstructor
public class SolicitacaoJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusSolicitacaoEnum status;

    @Column(nullable = false, name = "data_emissao")
    private Date dataEmissao;

    @Column(nullable = false, name = "data_upload")
    private Date dataUpload;

    @Column(nullable = false)
    private Double valor;

    @ManyToOne //TODO testar excluir funcionário
    @JoinColumn(name = "funcionario", referencedColumnName = "id", nullable = false)
    private UsuarioJpa funcionario;

    @Column
    private String descricao;

    @Column(name = "cnpj_servico")
    private String cnpjServico;

    @Column(nullable = false, unique = true, name = "path_arquivo")
    private String pathArquivo;

    public SolicitacaoJpa(Long id, StatusSolicitacaoEnum status, Date dataEmissao, Date dataUpload, Double valor, UsuarioJpa funcionario, String descricao, String cnpjServico, String pathArquivo) {
        validarStatus(status);
        validarDataEmissao(dataEmissao);
        validarDataUpload(dataUpload);
        validarValor(valor);
        validarFuncionario(funcionario);
        validarDescricao(descricao);
        validarCnpjServico(cnpjServico);
        validarPathArquivo(pathArquivo);

        this.id = id;
        this.status = status;
        this.dataEmissao = dataEmissao;
        this.dataUpload = dataUpload;
        this.valor = valor;
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.cnpjServico = cnpjServico;
        this.pathArquivo = pathArquivo;
    }

    private void validarStatus(StatusSolicitacaoEnum status) {
        if (Objects.isNull(status))
            throw new ValorInvalidoJpaException("a solicitação deve possuir um status.");
    }

    private void validarDataEmissao(Date dataEmissao) {
        if (Objects.isNull(dataEmissao))
            throw new ValorInvalidoJpaException("a solicitação deve possuir uma data de emissão.");
    }

    private void validarDataUpload(Date dataUpload) {
        if (Objects.isNull(dataUpload))
            throw new ValorInvalidoJpaException("a solicitação deve possuir uma data de upload.");
    }

    private void validarValor(Double valor) {
        if (Objects.isNull(valor))
            throw new ValorInvalidoJpaException("a solicitação deve possuir um valor.");
    }

    private void validarFuncionario(UsuarioJpa funcionario) {
        if (Objects.isNull(funcionario))
            throw new ValorInvalidoJpaException("a solicitação deve possuir um funcionário.");
    }

    private void validarDescricao(String descricao) {
        if (!Objects.isNull(descricao) && descricao.length() > 255)
            throw new ValorInvalidoJpaException("a descricao da solicitação deve possuir até 255 caracteres.");
    }

    private void validarCnpjServico(String cnpjServico) {
        if (cnpjServico.length() > 255)
            throw new ValorInvalidoJpaException("o CNPJ do serviço da solicitação deve possuir até 255 caracteres.");
    }

    private void validarPathArquivo(String pathArquivo) {
        if (Objects.isNull(pathArquivo))
            throw new ValorInvalidoJpaException("a solicitação deve possuir um caminho de arquivo.");

        if (pathArquivo.length() > 255)
            throw new ValorInvalidoJpaException("o caminho de arquivo da solicitação deve possuir até 255 caracteres.");
    }
}
