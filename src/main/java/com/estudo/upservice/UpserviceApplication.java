package com.estudo.upservice;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.estudo.upservice.service.DBService;

@SpringBootApplication
public class UpserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpserviceApplication.class, args);
	}
	
    @Autowired
    private DBService dbservice;

    @Bean
    public boolean instanciateDatabase() throws ParseException{
        dbservice.instanciateDatabase();
        return true;
    }
}
