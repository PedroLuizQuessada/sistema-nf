package com.example.sistemanf.infraestructure.persistence.jpa.mappers;

import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.infraestructure.persistence.jpa.models.UsuarioJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Objects;

@Component
@Profile("jpa")
public class UsuarioJpaDtoMapper {

    @Autowired
    private EmpresaJpaDtoMapper empresaJpaDtoMapper;

    public UsuarioJpa getJpa(UsuarioDto usuarioDto) {
        return new UsuarioJpa(usuarioDto.id(),
                !Objects.isNull(usuarioDto.empresa()) ? empresaJpaDtoMapper.getJpa(usuarioDto.empresa()) : null,
                usuarioDto.nome(), usuarioDto.tipo(), usuarioDto.email(), usuarioDto.senha(), new Date(usuarioDto.dataCriacao().getTime()),
                usuarioDto.ativo());
    }

    public UsuarioDto getDto(UsuarioJpa usuarioJpa) {
        return new UsuarioDto(usuarioJpa.getId(),
                !Objects.isNull(usuarioJpa.getEmpresa()) ? empresaJpaDtoMapper.getDto(usuarioJpa.getEmpresa()) : null,
                usuarioJpa.getNome(), usuarioJpa.getTipo(), usuarioJpa.getEmail(), usuarioJpa.getSenha(),
                new Date(usuarioJpa.getDataCriacao().getTime()), usuarioJpa.getAtivo());
    }
}
