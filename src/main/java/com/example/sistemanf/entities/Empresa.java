package com.example.sistemanf.entities;

import com.example.sistemanf.exceptions.ValorInvalidoException;
import com.example.sistemanf.utils.CnpjUtil;
import lombok.Getter;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Getter
public class Empresa {

    private final Long id; //TODO ID ao invés de UUID
    private final String nome;
    private final String cnpj;
    private final Date dataInclusao;

    public Empresa(Long id, String nome, String cnpj, Date dataInclusao) {
        validarNome(nome);
        CnpjUtil.validarCnpj(cnpj);

        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;

        this.dataInclusao = !Objects.isNull(dataInclusao) ? dataInclusao : new java.sql.Date(java.sql.Date.from(Instant.now()).getTime());
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome) || nome.isEmpty())
            throw new ValorInvalidoException("empresa deve possuir um nome.");
    }
}
