package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.dtos.LogDto;

public class LogGateway {

    private final LogDataSource logDataSource;

    public LogGateway(LogDataSource logDataSource) {
        this.logDataSource = logDataSource;
    }

    public void criarLog(LogDto criarLogDto) {
        logDataSource.criarLog(criarLogDto);
    }
}
