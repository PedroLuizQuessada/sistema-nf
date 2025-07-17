package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.LogJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
@Profile("jpa")
public class LogJpaDtoMapper {

    @Autowired
    private SolicitacaoJpaDtoMapper solicitacaoJpaDtoMapper;

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    public LogJpa getJpa(LogDto logDto) {
        return new LogJpa(logDto.id(),
                !Objects.isNull(logDto.solicitacao()) ? solicitacaoJpaDtoMapper.getJpa(logDto.solicitacao()) : null,
                !Objects.isNull(logDto.usuario()) ? usuarioJpaDtoMapper.getJpa(logDto.usuario()) : null,
                new Date(logDto.dataAlteracao().getTime()), logDto.acao());
    }

    public LogDto getDto(LogJpa logJpa) {
        return new LogDto(logJpa.getId(),
                !Objects.isNull(logJpa.getSolicitacao()) ? solicitacaoJpaDtoMapper.getDto(logJpa.getSolicitacao()) : null,
                !Objects.isNull(logJpa.getUsuario()) ? usuarioJpaDtoMapper.getDto(logJpa.getUsuario()) : null,
                new Date(logJpa.getDataAlteracao().getTime()), logJpa.getAcao());
    }
}
