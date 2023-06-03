package com.estudo.upservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.upservice.domain.Anuncio;
import com.estudo.upservice.domain.Cliente;

public interface AnuncioRepository extends JpaRepository<Anuncio,Integer>{
    
    @Transactional(readOnly  = true)
    Page<Anuncio> findByCliente(Cliente cliente, Pageable pageREquest);
}
