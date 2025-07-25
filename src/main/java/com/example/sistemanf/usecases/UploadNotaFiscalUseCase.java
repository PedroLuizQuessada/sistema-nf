package com.example.sistemanf.usecases;

import com.example.sistemanf.dtos.NotaFiscalDto;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.gateways.NotaFiscalGateway;
import com.example.sistemanf.gateways.SolicitacaoGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.SolicitacaoMapper;
import com.example.sistemanf.utils.FileUtil;
import com.example.sistemanf.utils.TimeUtil;

import java.io.File;

public class UploadNotaFiscalUseCase {

    private static final String UPLOAD_FILE_PATH = "src/main/resources/uploads/";
    private final NotaFiscalGateway notaFiscalGateway;
    private final SolicitacaoGateway solicitacaoGateway;
    private final UsuarioGateway usuarioGateway;

    public UploadNotaFiscalUseCase(NotaFiscalGateway notaFiscalGateway, SolicitacaoGateway solicitacaoGateway, UsuarioGateway usuarioGateway) {
        this.notaFiscalGateway = notaFiscalGateway;
        this.solicitacaoGateway = solicitacaoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public Solicitacao execute(UploadNotaFiscalRequest request, String emailRequester) {
        Usuario usuario = usuarioGateway.findUserByEmail(emailRequester);
        String nomeArquivo = emailRequester + "_" + TimeUtil.getNowFormatado() + request.extensaoArquivo();
        File file = FileUtil.converterBase64ToFile(request.arquivoBase64(), UPLOAD_FILE_PATH + nomeArquivo);
        NotaFiscalDto notaFiscalDto = notaFiscalGateway.getNotaFiscalDto(file);
        Solicitacao solicitacao = SolicitacaoMapper.getEntidade(request, notaFiscalDto, usuario, file.getAbsolutePath());
        return solicitacaoGateway.criarSolicitacao(SolicitacaoMapper.getDto(solicitacao));
    }
}
