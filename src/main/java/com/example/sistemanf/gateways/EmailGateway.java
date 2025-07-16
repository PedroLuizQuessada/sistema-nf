package com.example.sistemanf.gateways;

import com.example.sistemanf.datasources.EmailDataSource;

public class EmailGateway {

    private final EmailDataSource emailDataSource;

    public EmailGateway(EmailDataSource emailDataSource) {
        this.emailDataSource = emailDataSource;
    }

    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        emailDataSource.enviarEmail(destinatario, assunto, mensagem);
    }
}
