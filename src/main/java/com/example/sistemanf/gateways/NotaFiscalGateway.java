package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.NotaFiscalDataSource;

import java.io.File;

public class NotaFiscalGateway {

    private final NotaFiscalDataSource notaFiscalDataSource;

    public NotaFiscalGateway(NotaFiscalDataSource notaFiscalDataSource) {
        this.notaFiscalDataSource = notaFiscalDataSource;
    }

    public Double getValorTotal(File file) {
        return notaFiscalDataSource.getValorTotal(file);
    }
}
