package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
@Profile("jpa")
public class SolicitacaoJpaDtoMapper {

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    public SolicitacaoDto getDto(SolicitacaoJpa solicitacaoJpa) {
        return new SolicitacaoDto(solicitacaoJpa.getId(), solicitacaoJpa.getStatus(), solicitacaoJpa.getDataEmissao(), solicitacaoJpa.getDataUpload(),
                solicitacaoJpa.getValor(),
                !Objects.isNull(solicitacaoJpa.getFuncionario()) ? usuarioJpaDtoMapper.getDto(solicitacaoJpa.getFuncionario()) : null,
                solicitacaoJpa.getDescricao(), solicitacaoJpa.getCnpjServico(), solicitacaoJpa.getPathArquivo());
    }

    public SolicitacaoJpa getJpa(SolicitacaoDto solicitacaoDto) {
        return new SolicitacaoJpa(solicitacaoDto.id(), solicitacaoDto.status(), new Date(solicitacaoDto.dataEmissao().getTime()),
                new Date(solicitacaoDto.dataUpload().getTime()), solicitacaoDto.valor(),
                !Objects.isNull(solicitacaoDto.funcionario()) ? usuarioJpaDtoMapper.getJpa(solicitacaoDto.funcionario()) : null,
                solicitacaoDto.descricao(), solicitacaoDto.cnpjServico(), solicitacaoDto.pathArquivo());
    }
}
