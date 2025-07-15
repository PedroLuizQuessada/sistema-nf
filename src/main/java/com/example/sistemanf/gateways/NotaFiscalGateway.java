package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.dtos.NotaFiscalDto;

import java.io.File;
import java.util.Date;

public class NotaFiscalGateway {

    private final NotaFiscalDataSource notaFiscalDataSource;

    public NotaFiscalGateway(NotaFiscalDataSource notaFiscalDataSource) {
        this.notaFiscalDataSource = notaFiscalDataSource;
    }

    public NotaFiscalDto getNotaFiscalDto(File file) {
        String textoNotaFiscal = notaFiscalDataSource.getTexto(file);
        Double valor = notaFiscalDataSource.getValorTotal(textoNotaFiscal);
        Date dataEmissao = notaFiscalDataSource.getDataEmissao(textoNotaFiscal);
        String cnpjServico = notaFiscalDataSource.getCnpjServico(textoNotaFiscal);
        return new NotaFiscalDto(valor, dataEmissao, cnpjServico);
    }
}
