package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.EmpresaDto;

import java.util.Optional;

public interface EmpresaDataSource {
    Optional<EmpresaDto> findEmpresaById(Long id);
}
