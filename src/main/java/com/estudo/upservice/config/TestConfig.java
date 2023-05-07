package com.estudo.upservice.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estudo.upservice.service.DBService;
import com.estudo.upservice.service.EmailService;
import com.estudo.upservice.service.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
    
    @Autowired
    private DBService dbservice;

    @Bean
    public boolean instanciateDatabase() throws ParseException{
        dbservice.instanciateDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
