package com.example.sistemanf.dtos;

import java.util.Date;

public record EmpresaDto(Long id, String nome, String cnpj, Date dataInclusao) {
}
