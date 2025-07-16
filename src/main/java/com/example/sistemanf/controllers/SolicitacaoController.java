package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.requests.AtualizarStatusSolicitacaoRequest;
import com.example.sistemanf.dtos.requests.ConsultarSolicitacoesRequest;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.gateways.LogGateway;
import com.example.sistemanf.gateways.NotaFiscalGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;
import com.example.sistemanf.usecases.AtualizarStatusSolicitacaoUseCase;
import com.example.sistemanf.usecases.CancelarSolicitacaoUseCase;
import com.example.sistemanf.usecases.ConsultarSolicitacoesUseCase;
import com.example.sistemanf.usecases.UploadNotaFiscalUseCase;

import java.util.List;

public class SolicitacaoController {

    private final NotaFiscalDataSource notaFiscalDataSource;
    private final SolicitacaoDataSource solicitacaoDataSource;
    private final UsuarioDataSource usuarioDataSource;
    private final LogDataSource logDataSource;

    public SolicitacaoController(NotaFiscalDataSource notaFiscalDataSource, SolicitacaoDataSource solicitacaoDataSource,
                                 UsuarioDataSource usuarioDataSource, LogDataSource logDataSource) {
        this.notaFiscalDataSource = notaFiscalDataSource;
        this.solicitacaoDataSource = solicitacaoDataSource;
        this.usuarioDataSource = usuarioDataSource;
        this.logDataSource = logDataSource;
    }

    public SolicitacaoDto uploadNotaFiscal(UploadNotaFiscalRequest request, String emailRequester) {
        NotaFiscalGateway notaFiscalGateway = new NotaFiscalGateway(notaFiscalDataSource);
        SolicitacaoGateway solicitacaoGateway = new SolicitacaoGateway(solicitacaoDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        UploadNotaFiscalUseCase useCase = new UploadNotaFiscalUseCase(notaFiscalGateway, solicitacaoGateway, usuarioGateway);
        return SolicitacaoMapper.getResponse(useCase.execute(request, emailRequester));
    }

    public void cancelarSolicitacao(Long id, String requesterEmail) {
        SolicitacaoGateway solicitacaoGateway = new SolicitacaoGateway(solicitacaoDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        LogGateway logGateway = new LogGateway(logDataSource);
        CancelarSolicitacaoUseCase useCase = new CancelarSolicitacaoUseCase(solicitacaoGateway, usuarioGateway, logGateway);
        useCase.execute(id, requesterEmail);
    }

    public SolicitacaoDto atualizarStatusSolicitacao(AtualizarStatusSolicitacaoRequest request, Long id, String requesterEmail) {
        SolicitacaoGateway solicitacaoGateway = new SolicitacaoGateway(solicitacaoDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        LogGateway logGateway = new LogGateway(logDataSource);
        AtualizarStatusSolicitacaoUseCase useCase = new AtualizarStatusSolicitacaoUseCase(solicitacaoGateway, usuarioGateway, logGateway);
        return SolicitacaoMapper.getResponse(useCase.execute(request, id, requesterEmail));
    }

    public List<SolicitacaoDto> consultarSolicitacoes(ConsultarSolicitacoesRequest request, String requesterEmail) {
        SolicitacaoGateway solicitacaoGateway = new SolicitacaoGateway(solicitacaoDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ConsultarSolicitacoesUseCase useCase = new ConsultarSolicitacoesUseCase(solicitacaoGateway, usuarioGateway);
        List<Solicitacao> solicitacaoList = useCase.execute(request, requesterEmail);
        return solicitacaoList.stream().map(SolicitacaoMapper::getResponse).toList();
    }
}
