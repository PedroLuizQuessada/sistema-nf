package com.example.sistemanf.entities;

import com.example.sistemanf.enums.StatusSolicitacaoEnum;
import com.example.sistemanf.exceptions.ValorInvalidoException;
import com.example.sistemanf.utils.CnpjUtil;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Getter
public class Solicitacao {

    private final Long id;
    private final StatusSolicitacaoEnum status;
    private final Date dataEmissao;
    private final Date dataUpload;
    private final Double valor;
    private final Usuario funcionario;
    private final String descricao;
    private final String cnpjServico;
    private final String pathArquivo;

    public Solicitacao(Long id, StatusSolicitacaoEnum status, Date dataEmissao, Date dataUpload, Double valor,
                       Usuario funcionario, String descricao, String cnpjServico, String pathArquivo) {
        validarStatus(status);
        validarDataEmissao(dataEmissao);
        validarValor(valor);
        validarFuncionario(funcionario);
        CnpjUtil.validarCnpj(cnpjServico);
        validarPathArquivo(pathArquivo);

        this.id = id;
        this.status = status;
        this.dataEmissao = dataEmissao;
        this.valor = valor;
        this.funcionario = funcionario;
        this.descricao = descricao;
        this.cnpjServico = cnpjServico;
        this.pathArquivo = pathArquivo;

        this.dataUpload = !Objects.isNull(dataUpload) ? dataUpload : new java.sql.Date(java.sql.Date.from(Instant.now()).getTime());
    }

    private void validarStatus(StatusSolicitacaoEnum status) {
        if (Objects.isNull(status))
            throw new ValorInvalidoException("solicitação deve possuir um status.");
    }

    private void validarDataEmissao(Date dataEmissao) {
        if (Objects.isNull(dataEmissao))
            throw new ValorInvalidoException("solicitação deve possuir uma data de emissão.");
    }

    private void validarValor(Double valor) {
        if (Objects.isNull(valor))
            throw new ValorInvalidoException("solicitação deve possuir um valor.");
    }

    private void validarFuncionario(Usuario funcionario) {
        if (Objects.isNull(funcionario))
            throw new ValorInvalidoException("solicitação deve possuir um funcionário.");
    }

    private void validarPathArquivo(String pathArquivo) {
        if (Objects.isNull(pathArquivo))
            throw new ValorInvalidoException("solicitação deve possuir um path de arquivo.");
    }
}
