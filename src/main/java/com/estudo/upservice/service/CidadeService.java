package com.estudo.upservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Cidade;
import com.estudo.upservice.repository.CidadeRepository;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository repo;


    public List<Cidade> findByEstado(Integer estadoId){
        return repo.findCidades(estadoId);
    }

}