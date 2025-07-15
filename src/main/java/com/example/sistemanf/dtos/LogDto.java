package com.example.sistemanf.dtos;

import java.util.Date;

public record LogDto(Long id, SolicitacaoDto solicitacao, UsuarioDto usuario, Date dataAlteracao, String acao) {
}
