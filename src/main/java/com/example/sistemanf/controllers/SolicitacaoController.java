package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.NotaFiscalDataSource;
import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.gateways.NotaFiscalGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;
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
}
