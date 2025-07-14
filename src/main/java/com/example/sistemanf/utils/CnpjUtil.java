package com.example.sistemanf.utils;

import com.example.sistemanf.exceptions.ValorInvalidoException;

import java.util.Objects;

public class CnpjUtil {

    private CnpjUtil() {}

    public static void validarCnpj(String cnpj) {
        if (Objects.isNull(cnpj) || cnpj.isEmpty())
            throw new ValorInvalidoException("empresa deve possuir um CNPJ.");

        if (cnpj.length() != 14)
            throw new ValorInvalidoException("CNPJ inválido.");
    }
}
