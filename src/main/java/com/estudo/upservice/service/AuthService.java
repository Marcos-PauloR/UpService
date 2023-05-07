package com.estudo.upservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private BCryptPasswordEncoder pe;


    private Random rand = new Random();

    public void sendNewPassword(String email) {
        Cliente cliente = repo.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }
        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));

        repo.save(cliente);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i=0;i<10;i++){
            vet[i]=randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if(opt ==0){
            return (char) (rand.nextInt(26)+65);
        }else if (opt ==1){
            return (char) (rand.nextInt(26)+65);
        }else{
            return (char) (rand.nextInt(26)+97);
        }
    }

}
