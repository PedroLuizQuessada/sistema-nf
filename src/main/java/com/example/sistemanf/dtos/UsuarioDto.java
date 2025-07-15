package com.example.sistemanf.dtos;

import com.example.sistemanf.enums.TipoUsuarioEnum;

import java.util.Date;

public record UsuarioDto(Long id, EmpresaDto empresa, String nome, TipoUsuarioEnum tipo, String email, String senha, Date dataCriacao, Boolean ativo) {
}
