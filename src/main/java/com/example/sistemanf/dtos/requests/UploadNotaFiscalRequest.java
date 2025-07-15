package com.example.sistemanf.dtos.requests;

import java.util.Date;

//TODO tirar dataEmissao e cnpjServico da NF ao invés de receber como parâmetro
public record UploadNotaFiscalRequest(Date dataEmissao, String descricao, String cnpjServico, String arquivoBase64, String extensaoArquivo) {
}
