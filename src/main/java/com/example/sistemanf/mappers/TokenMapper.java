package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.entities.Token;

public class TokenMapper {

    private TokenMapper() {}

    public static Token getEntidade(TokenDto tokenDto) {
        return new Token(tokenDto.token(), tokenDto.email());
    }

    public static TokenDto getDto(Token token) {
        return new TokenDto(token.getToken(), token.getEmail());
    }
}
