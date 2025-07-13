package com.example.sistemanf.controllers;

import com.example.sistemanf.datasources.EmpresaDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
import com.example.sistemanf.gateways.EmpresaGateway;
import com.example.sistemanf.gateways.UsuarioGateway;
import com.example.sistemanf.mappers.UsuarioMapper;
import com.example.sistemanf.usecases.CriarUsuarioFuncionarioUseCase;

public class UsuarioController {

    private final UsuarioDataSource usuarioDataSource;
    private final EmpresaDataSource empresaDataSource;

    public UsuarioController(UsuarioDataSource usuarioDataSource, EmpresaDataSource empresaDataSource) {
        this.usuarioDataSource = usuarioDataSource;
        this.empresaDataSource = empresaDataSource;
    }

    public UsuarioDto criarUsuarioFuncionario(CriarUsuarioFuncionarioRequest request) {
        UsuarioGateway usuarioGateway = new UsuarioGateway(usuarioDataSource);
        EmpresaGateway empresaGateway = new EmpresaGateway(empresaDataSource);
        CriarUsuarioFuncionarioUseCase useCase = new CriarUsuarioFuncionarioUseCase(usuarioGateway, empresaGateway);
        return UsuarioMapper.getDto(useCase.execute(request));
    }
}
