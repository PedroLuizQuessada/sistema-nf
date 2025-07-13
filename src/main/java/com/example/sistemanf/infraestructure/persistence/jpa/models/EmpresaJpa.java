package com.example.sistemanf.infraestructure.persistence.jpa.models;

import com.example.sistemanf.infraestructure.exceptions.ValorInvalidoJpaException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "empresas")
@Getter
@NoArgsConstructor
public class EmpresaJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false, name = "data_inclusao")
    private Date dataInclusao;

    public EmpresaJpa(Long id, String nome, String cnpj, Date dataInclusao) {
        validarNome(nome);
        validarCnpj(cnpj);
        validarDataInclusao(dataInclusao);

        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.dataInclusao = dataInclusao;
    }

    private void validarNome(String nome) {
        if (Objects.isNull(nome))
            throw new ValorInvalidoJpaException("a empresa deve possuir um nome.");

        if (nome.length() > 255)
            throw new ValorInvalidoJpaException("o nome da empresa deve possuir até 255 caracteres.");
    }

    private void validarCnpj(String cnpj) {
        if (Objects.isNull(cnpj))
            throw new ValorInvalidoJpaException("a empresa deve possuir um CNPJ.");

        if (cnpj.length() > 14)
            throw new ValorInvalidoJpaException("o CNPJ da empresa deve possuir até 14 caracteres.");
    }

    private void validarDataInclusao(Date dataInclusao) {
        if (Objects.isNull(dataInclusao))
            throw new ValorInvalidoJpaException("a empresa deve possuir uma data de inclusão.");
    }
}
