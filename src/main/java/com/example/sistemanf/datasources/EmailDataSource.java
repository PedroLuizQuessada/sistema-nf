package com.example.sistemanf.datasources;

public interface EmailDataSource {
    void enviarEmail(String destinatario, String assunto, String mensagem);
}
