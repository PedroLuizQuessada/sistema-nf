package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.requests.AtualizarStatusSolicitacaoRequest;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.gateways.NotaFiscalGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;
import com.example.sistemanf.usecases.AtualizarStatusSolicitacaoUseCase;
import com.example.sistemanf.usecases.CancelarSolicitacaoUseCase;
import com.example.sistemanf.usecases.UploadNotaFiscalUseCase;

public class SolicitacaoController {

    private final NotaFiscalDataSource notaFiscalDataSource;
    private final SolicitacaoDataSource solicitacaoDataSource;
    private final UsuarioDataSource usuarioDataSource;

    public SolicitacaoController(NotaFiscalDataSource notaFiscalDataSource, SolicitacaoDataSource solicitacaoDataSource, UsuarioDataSource usuarioDataSource) {
        this.notaFiscalDataSource = notaFiscalDataSource;
        this.solicitacaoDataSource = solicitacaoDataSource;
        this.usuarioDataSource = usuarioDataSource;
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
        CancelarSolicitacaoUseCase useCase = new CancelarSolicitacaoUseCase(solicitacaoGateway);
        useCase.execute(id, requesterEmail);
    }

    public SolicitacaoDto atualizarStatusSolicitacao(AtualizarStatusSolicitacaoRequest request, Long id) {
        SolicitacaoGateway solicitacaoGateway = new SolicitacaoGateway(solicitacaoDataSource);
        AtualizarStatusSolicitacaoUseCase useCase = new AtualizarStatusSolicitacaoUseCase(solicitacaoGateway);
        return SolicitacaoMapper.getResponse(useCase.execute(request, id));
    }
}
