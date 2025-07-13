package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.EmpresaDataSource;
import com.example.sistemanf.dtos.EmpresaDto;
import com.example.sistemanf.entities.Empresa;
import com.example.sistemanf.exceptions.EmpresaNotFoundException;
import com.example.sistemanf.mappers.EmpresaMapper;

import java.util.Objects;
import java.util.Optional;

public class EmpresaGateway {

    private final EmpresaDataSource empresaDataSource;

    public EmpresaGateway(EmpresaDataSource empresaDataSource) {
        this.empresaDataSource = empresaDataSource;
    }

    public Empresa findEmpresaById(Long id) {
        if (Objects.isNull(id))
            throw new EmpresaNotFoundException();

        Optional<EmpresaDto> optionalEmpresaDto = empresaDataSource.findEmpresaById(id);

        if (optionalEmpresaDto.isEmpty())
            throw new EmpresaNotFoundException();

        return EmpresaMapper.getEntidade(optionalEmpresaDto.get());
    }
}
