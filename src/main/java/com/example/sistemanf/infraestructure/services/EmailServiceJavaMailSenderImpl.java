package com.example.sistemanf.infraestructure.services;

import com.example.sistemanf.datasources.EmailDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("javamailsender")
public class EmailServiceJavaMailSenderImpl implements EmailDataSource {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarEmail(String destinatario, String assunto, String mensagem) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("noreply@email.com");
        mailMessage.setTo(destinatario);
        mailMessage.setSubject(assunto);
        mailMessage.setText(mensagem);
        javaMailSender.send(mailMessage);
    }
}
