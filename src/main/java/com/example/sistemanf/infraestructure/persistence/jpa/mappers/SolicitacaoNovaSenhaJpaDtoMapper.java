package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoNovaSenhaJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Profile("jpa")
public class SolicitacaoNovaSenhaJpaDtoMapper {

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    public SolicitacaoNovaSenhaJpa getJpa(SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto) {
        return new SolicitacaoNovaSenhaJpa(solicitacaoNovaSenhaDto.id(),
                !Objects.isNull(solicitacaoNovaSenhaDto.usuarioDto()) ? usuarioJpaDtoMapper.getJpa(solicitacaoNovaSenhaDto.usuarioDto()) : null,
                solicitacaoNovaSenhaDto.codigo(), solicitacaoNovaSenhaDto.dataExpiracao(), solicitacaoNovaSenhaDto.ativo());
    }

    public SolicitacaoNovaSenhaDto getDto(SolicitacaoNovaSenhaJpa solicitacaoNovaSenhaJpa) {
        return new SolicitacaoNovaSenhaDto(solicitacaoNovaSenhaJpa.getId(),
                !Objects.isNull(solicitacaoNovaSenhaJpa.getUsuario()) ? usuarioJpaDtoMapper.getDto(solicitacaoNovaSenhaJpa.getUsuario()) : null,
                solicitacaoNovaSenhaJpa.getCodigo(), solicitacaoNovaSenhaJpa.getDataExpiracao(), solicitacaoNovaSenhaJpa.getAtivo());
    }
}
