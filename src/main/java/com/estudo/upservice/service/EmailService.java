package com.estudo.upservice.service;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Anuncio;
import com.estudo.upservice.domain.Cliente;

@Service
public interface EmailService {
    

    void sendOrderConfirmationEmail(Anuncio anuncio);

    void sendEmail(SimpleMailMessage mailMessage);

    void sendOrderConfirmationEmailHtmlEmail(Anuncio anuncio);

    void sendHtmlEmail(MimeMessage mailMessage);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
