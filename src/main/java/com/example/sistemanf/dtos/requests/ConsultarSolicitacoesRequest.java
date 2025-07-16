package com.example.sistemanf.dtos.requests;

import com.example.sistemanf.enums.OrdenacaoConsultaSolicitacoesEnum;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;

import java.util.Date;

public record ConsultarSolicitacoesRequest(int page, int size, Long id, OrdenacaoConsultaSolicitacoesEnum ordenacao, boolean sentidoOrdenacao,
                                           StatusSolicitacaoEnum status, Date dataEmissaoInicio, Date dataEmissaoFim, Date dataUploadInicio,
                                           Date dataUploadFim, Double valor, String nomeFuncionario, String descricao, String cnpjServico) {
}
