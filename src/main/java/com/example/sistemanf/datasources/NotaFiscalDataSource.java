package com.example.sistemanf.datasources;

import java.io.File;
import java.util.Date;

public interface NotaFiscalDataSource {
    String getTexto(File file);
    Double getValorTotal(String textoNotaFiscal);
    Date getDataEmissao(String textoNotaFiscal);
    String getCnpjServico(String textoNotaFiscal);
}
