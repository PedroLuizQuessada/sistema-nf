package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.enums.OrdenacaoConsultaSolicitacoesEnum;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;
import com.example.sistemanf.exceptions.SolicitacaoNotFoundException;
import com.example.sistemanf.mappers.SolicitacaoMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class SolicitacaoGateway {

    private final SolicitacaoDataSource solicitacaoDataSource;

    public SolicitacaoGateway(SolicitacaoDataSource solicitacaoDataSource) {
        this.solicitacaoDataSource = solicitacaoDataSource;
    }

    public Solicitacao criarSolicitacao(SolicitacaoDto criarSolicitacaoDto) {
        SolicitacaoDto solicitacaoDto = solicitacaoDataSource.criarSolicitacao(criarSolicitacaoDto);
        return SolicitacaoMapper.getEntidade(solicitacaoDto);
    }

    public Solicitacao findSolicitacaoByIdAndEmailFuncionario(Long id, String email) {
        Optional<SolicitacaoDto> solicitacaoDtoOptional = solicitacaoDataSource.findSolicitacaoByIdAndEmailFuncionario(id, email);

        if (solicitacaoDtoOptional.isEmpty())
            throw new SolicitacaoNotFoundException();

        return SolicitacaoMapper.getEntidade(solicitacaoDtoOptional.get());
    }

    public Solicitacao findSolicitacaoById(Long id) {
        Optional<SolicitacaoDto> solicitacaoDtoOptional = solicitacaoDataSource.findSolicitacaoById(id);

        if (solicitacaoDtoOptional.isEmpty())
            throw new SolicitacaoNotFoundException();

        return SolicitacaoMapper.getEntidade(solicitacaoDtoOptional.get());
    }

    public void cancelarSolicitacao(SolicitacaoDto solicitacaoDto) {
        solicitacaoDataSource.cancelarSolicitacao(solicitacaoDto);
    }

    public Solicitacao atualizarSolicitacao(SolicitacaoDto atualizarSolicitacaoDto) {
        SolicitacaoDto solicitacaoDto = solicitacaoDataSource.atualizarSolicitacao(atualizarSolicitacaoDto);
        return SolicitacaoMapper.getEntidade(solicitacaoDto);
    }

    public List<Solicitacao> consultarSolicitacoes(int page, int size, OrdenacaoConsultaSolicitacoesEnum ordenacao, boolean sentidoOrdenacao,
                                                   Long empresa, Long id, StatusSolicitacaoEnum status, Date dataEmissaoInicio,
                                                   Date dataEmissaoFim, Date dataUploadInicio, Date dataUploadFim, Double valor,
                                                   String nomeFuncionario, String descricao, String cnpjServico) {
        List<SolicitacaoDto> solicitacaoDtoList = solicitacaoDataSource.consultarSolicitacoes(page, size, ordenacao, sentidoOrdenacao,
                empresa, id, status, dataEmissaoInicio, dataEmissaoFim, dataUploadInicio, dataUploadFim, valor, nomeFuncionario,
                descricao, cnpjServico);
        return solicitacaoDtoList.stream().map(SolicitacaoMapper::getEntidade).toList();
    }
}
