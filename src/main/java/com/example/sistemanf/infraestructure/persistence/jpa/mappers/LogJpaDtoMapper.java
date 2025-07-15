package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.LogJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
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
}
