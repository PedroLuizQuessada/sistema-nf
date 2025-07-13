package com.example.sistemanf.entities;

import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.exceptions.RequesterException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Requester {
    private final TipoUsuarioEnum tipo;
    private final String email;

    public Requester(TipoUsuarioEnum tipo, String email) {

        validarTipo(tipo);
        validarEmail(email);

        this.tipo = tipo;
        this.email = email;
    }

    private void validarTipo(TipoUsuarioEnum tipo) {
        if (Objects.isNull(tipo))
            throw new RequesterException("Falha ao detectar o tipo de usuário do requisitor. Favor contactar o administrador");
    }

    private void validarEmail(String email) {
        if (Objects.isNull(email) || email.isEmpty())
            throw new RequesterException("Falha ao detectar o e-mail do requisitor. Favor contactar o administrador");
    }

}
