package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.entities.Log;
import com.example.sistemanf.gateways.LogGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.LogMapper;
import com.example.sistemanf.usecases.ConsultarLogsUseCase;

import java.util.List;

public class LogController {

    private final LogDataSource logDataSource;
    private final UsuarioDataSource usuarioDataSource;

    public LogController(LogDataSource logDataSource, UsuarioDataSource usuarioDataSource) {
        this.logDataSource = logDataSource;
        this.usuarioDataSource = usuarioDataSource;
    }

    public List<LogDto> consultarLogs(int page, int size, Long solicitacaoId, String requesterEmail) {
        LogGateway logGateway = new LogGateway(logDataSource);
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        ConsultarLogsUseCase useCase = new ConsultarLogsUseCase(logGateway, usuarioGateway);
        List<Log> logList = useCase.execute(page, size, solicitacaoId, requesterEmail);
        return logList.stream().map(LogMapper::getResponse).toList();
    }
}
