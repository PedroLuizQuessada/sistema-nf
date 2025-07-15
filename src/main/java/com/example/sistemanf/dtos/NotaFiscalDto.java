package com.example.sistemanf.dtos;

import java.util.Date;

public record NotaFiscalDto(Double valor, Date dataEmissao, String cnpjServico) {
}
