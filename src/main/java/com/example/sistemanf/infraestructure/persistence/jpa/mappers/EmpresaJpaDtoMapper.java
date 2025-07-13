package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.EmpresaDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.EmpresaJpa;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class EmpresaJpaDtoMapper {

    public EmpresaJpa getJpa(EmpresaDto empresaDto) {
        return new EmpresaJpa(empresaDto.id(), empresaDto.nome(), empresaDto.cnpj(), new Date(empresaDto.dataInclusao().getTime()));
    }

    public EmpresaDto getDto(EmpresaJpa empresaJpa) {
        return new EmpresaDto(empresaJpa.getId(), empresaJpa.getNome(), empresaJpa.getCnpj(), empresaJpa.getDataInclusao());
    }
}
