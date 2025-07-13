package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.enums.TipoUsuarioEnum;

public interface TokenDataSource {
    TokenDto gerarToken(TipoUsuarioEnum tipo, String email);
    RequesterDto getRequester(String token);
}
