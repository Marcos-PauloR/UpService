package com.estudo.upservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Estado;
import com.estudo.upservice.repository.EstadoRepository;

@Service
public class EstadoService {
    
    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll(){
        return repo.findAllByOrderByNome();
    }

}
