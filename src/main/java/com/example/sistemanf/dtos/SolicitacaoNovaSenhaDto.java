package com.example.sistemanf.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

public record SolicitacaoNovaSenhaDto(Long id, UsuarioDto usuarioDto, @JsonInclude(JsonInclude.Include.NON_NULL) String codigo,
                                      LocalDateTime dataExpiracao, Boolean ativo) {
}
