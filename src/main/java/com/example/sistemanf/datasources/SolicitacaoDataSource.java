package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.enums.OrdenacaoConsultaSolicitacoesEnum;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SolicitacaoDataSource {
    SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto);
    Optional<SolicitacaoDto> findSolicitacaoByIdAndEmailFuncionario(Long id, String email);
    Optional<SolicitacaoDto> findSolicitacaoById(Long id);
    void cancelarSolicitacao(SolicitacaoDto solicitacaoDto);
    SolicitacaoDto atualizarSolicitacao(SolicitacaoDto solicitacaoDto);
    List<SolicitacaoDto> consultarSolicitacoes(int page, int size, OrdenacaoConsultaSolicitacoesEnum ordenacao, boolean sentidoOrdenacao,
                                               Long empresa, Long id, StatusSolicitacaoEnum status, Date dataEmissaoInicio,
                                               Date dataEmissaoFim, Date dataUploadInicio, Date dataUploadFim, Double valor,
                                               String nomeFuncionario, String descricao, String cnpjServico);
}
