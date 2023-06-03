package com.estudo.upservice.service;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.estudo.upservice.domain.Anuncio;
import com.estudo.upservice.domain.Cliente;

public abstract class AbstractEmailService implements EmailService{
    
    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendOrderConfirmationEmail(Anuncio anuncio){
        SimpleMailMessage sm = prepareSimpleMailFromPredido(anuncio);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailFromPredido(Anuncio anuncio) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(anuncio.getCliente().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Confirmação de Anuncio! Código: "+anuncio.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(anuncio.toString());
        return sm;
    }

    protected String htmlFromTemplatePedido(Anuncio anuncio){
        Context context = new Context();

        context.setVariable("anuncio", anuncio);
        return templateEngine.process("email/confirmacaoPedido", context);
    }

    @Override
    public void sendOrderConfirmationEmailHtmlEmail(Anuncio anuncio){
        try{
            MimeMessage mm = prepareMimeMessageFromPedido(anuncio);
            sendHtmlEmail(mm);        
        }catch(MessagingException e){
            sendOrderConfirmationEmail(anuncio);
        }
    }

    private MimeMessage prepareMimeMessageFromPedido(Anuncio anuncio) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(anuncio.getCliente().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Anuncio Confirmado! Codigo"+anuncio.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(anuncio), true);
        return mimeMessage;
    }

    @Override
    public void sendNewPasswordEmail(Cliente cliente, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(cliente.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de ALteração de Senha!");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova Senha: "+newPass);
        return sm;
    }


}
