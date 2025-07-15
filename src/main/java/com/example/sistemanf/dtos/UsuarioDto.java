package com.example.sistemanf.dtos;

import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public record UsuarioDto(Long id, EmpresaDto empresa, String nome, TipoUsuarioEnum tipo, String email,
                         @JsonInclude(JsonInclude.Include.NON_NULL) String senha, Date dataCriacao, Boolean ativo) {
}
