package com.estudo.upservice.repository;

import com.estudo.upservice.domain.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{
    
}
