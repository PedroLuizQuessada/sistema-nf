package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.entities.Log;
import com.example.sistemanf.mappers.LogMapper;

import java.util.List;

public class LogGateway {

    private final LogDataSource logDataSource;

    public LogGateway(LogDataSource logDataSource) {
        this.logDataSource = logDataSource;
    }

    public void criarLog(LogDto criarLogDto) {
        logDataSource.criarLog(criarLogDto);
    }

    public List<Log> consultarLogs(int page, int size, Long solicitacao, Long empresa, Long funcionario) {
        List<LogDto> logDtoList = logDataSource.consultarLogs(page, size, solicitacao, empresa, funcionario);
        return logDtoList.stream().map(LogMapper::getEntidade).toList();
    }
}
