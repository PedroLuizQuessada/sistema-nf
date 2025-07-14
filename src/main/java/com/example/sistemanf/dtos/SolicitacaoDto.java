package com.example.sistemanf.dtos;

import com.example.sistemanf.enums.StatusSolicitacaoEnum;

import java.util.Date;

public record SolicitacaoDto(Long id, StatusSolicitacaoEnum status, Date dataEmissao, Date dataUpload, Double valor,
                             UsuarioDto funcionario, String descricao, String cnpjServico, String pathArquivo) {
}
