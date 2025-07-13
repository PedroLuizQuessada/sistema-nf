package com.example.sistemanf.entities;

import com.example.sistemanf.exceptions.TokenException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Token {
    private final String token;
    private final String email;

    public Token(String token, String email) {

        validarToken(token);
        validarEmail(email);

        this.token = token;
        this.email = email;
    }

    private void validarToken(String token) {
        if (Objects.isNull(token) || token.isEmpty())
            throw new TokenException("O token deve possuir um valor");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email) || email.isEmpty())
            throw new TokenException("O e-mail do token deve possuir um valor");
    }
}
