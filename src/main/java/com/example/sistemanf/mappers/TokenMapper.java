package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.entities.Token;

public class TokenMapper {

    public static Token getEntidade(TokenDto tokenDto) {
        return new Token(tokenDto.token(), tokenDto.email());
    }
}
