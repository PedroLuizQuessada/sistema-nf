package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;

import java.util.Objects;

public class SolicitacaoMapper {

    private SolicitacaoMapper() {}

    public static Solicitacao getEntidade(UploadNotaFiscalRequest request, Double valor, Usuario funcionario, String pathArquivo) {
        return new Solicitacao(null, StatusSolicitacaoEnum.PENDENTE, request.dataEmissao(), null, valor, funcionario, request.descricao(),
                request.cnpjServico(), pathArquivo);
    }

    public static Solicitacao getEntidade(SolicitacaoDto solicitacaoDto) {
        return new Solicitacao(solicitacaoDto.id(), solicitacaoDto.status(), solicitacaoDto.dataEmissao(), solicitacaoDto.dataUpload(),
                solicitacaoDto.valor(),
                !Objects.isNull(solicitacaoDto.funcionario()) ? UsuarioMapper.getEntidade(solicitacaoDto.funcionario(), false) : null,
                solicitacaoDto.descricao(), solicitacaoDto.cnpjServico(), solicitacaoDto.pathArquivo());
    }

    public static SolicitacaoDto getDto(Solicitacao solicitacao) {
        return new SolicitacaoDto(solicitacao.getId(), solicitacao.getStatus(), solicitacao.getDataEmissao(), solicitacao.getDataUpload(),
                solicitacao.getValor(),
                !Objects.isNull(solicitacao.getFuncionario()) ? UsuarioMapper.getDto(solicitacao.getFuncionario()) : null,
                solicitacao.getDescricao(), solicitacao.getCnpjServico(), solicitacao.getPathArquivo());
    }
}
