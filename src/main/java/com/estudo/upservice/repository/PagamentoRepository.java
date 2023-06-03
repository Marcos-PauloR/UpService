package com.estudo.upservice.repository;

import com.estudo.upservice.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Integer>{
    
}
