package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.LogDto;

import java.util.List;

public interface LogDataSource {
    void criarLog(LogDto logDto);
    List<LogDto> consultarLogs(int page, int size, Long solicitacao, Long empresa, Long funcionario);
}
