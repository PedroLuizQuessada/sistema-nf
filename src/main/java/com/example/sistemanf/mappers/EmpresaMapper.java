package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.EmpresaDto;
import com.example.sistemanf.entities.Empresa;

public class EmpresaMapper {

    private EmpresaMapper(){}

    public static Empresa getEntidade(EmpresaDto empresaDto) {
        return new Empresa(empresaDto.id(), empresaDto.nome(), empresaDto.cnpj(), empresaDto.dataInclusao());
    }

    public static EmpresaDto getDto(Empresa empresa) {
        return new EmpresaDto(empresa.getId(), empresa.getNome(), empresa.getCnpj(), empresa.getDataInclusao());
    }
}
