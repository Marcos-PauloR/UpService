package com.estudo.upservice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.upservice.domain.Categoria;
import com.estudo.upservice.domain.Servico;

public interface ServicoRepository extends JpaRepository<Servico,Integer>{
 
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Servico obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Servico> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

    @Transactional(readOnly = true)
    Page<Servico> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
