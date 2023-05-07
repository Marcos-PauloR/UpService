package com.estudo.upservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.security.UserSS;

@Service
public class UserDatilsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cli = clienteRepository.findByEmail(username);
        
        if(cli==null){
            throw new UsernameNotFoundException(username);
        }        
        return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    }
    
}
