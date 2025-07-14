package com.example.sistemanf.dtos.requests;

import java.util.Date;

public record UploadNotaFiscalRequest(Date dataEmissao, String descricao, String cnpjServico, String arquivoBase64, String extensaoArquivo) {
}
