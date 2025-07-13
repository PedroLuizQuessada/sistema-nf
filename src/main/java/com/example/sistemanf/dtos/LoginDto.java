package com.example.sistemanf.dtos;

import com.example.sistemanf.enums.TipoUsuarioEnum;

public record LoginDto(String email, String senha, TipoUsuarioEnum tipo) {
}
