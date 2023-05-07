package com.estudo.upservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.upservice.domain.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer>{
    

@Transactional(readOnly = true)
List<Estado> findAllByOrderByNome();

}
