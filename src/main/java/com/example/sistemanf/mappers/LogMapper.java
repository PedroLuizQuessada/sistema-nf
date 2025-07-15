package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.entities.Log;
import com.example.sistemanf.entities.Solicitacao;
import com.example.sistemanf.entities.Usuario;

import java.util.Objects;

public class LogMapper {

    private LogMapper() {}

    public static Log getEntidade(Solicitacao solicitacao, Usuario usuario, String acao) {
        return new Log(null, solicitacao, usuario, null, acao);
    }

    public static Log getEntidade(LogDto logDto) {
        return new Log(logDto.id(),
                !Objects.isNull(logDto.solicitacao()) ? SolicitacaoMapper.getEntidade(logDto.solicitacao()) : null,
                !Objects.isNull(logDto.usuario()) ? UsuarioMapper.getEntidade(logDto.usuario(), false) : null,
                logDto.dataAlteracao(), logDto.acao());
    }

    public static LogDto getDto(Log log) {
        return new LogDto(log.getId(),
                !Objects.isNull(log.getSolicitacao()) ? SolicitacaoMapper.getDto(log.getSolicitacao()) : null,
                !Objects.isNull(log.getUsuario()) ? UsuarioMapper.getDto(log.getUsuario()) : null,
                log.getDataAlteracao(), log.getAcao());
    }
}
