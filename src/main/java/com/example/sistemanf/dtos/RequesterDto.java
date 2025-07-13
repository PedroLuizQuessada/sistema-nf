package com.example.sistemanf.dtos;

import com.example.sistemanf.enums.TipoUsuarioEnum;

public record RequesterDto(TipoUsuarioEnum tipo, String email) {
}
