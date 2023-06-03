package com.estudo.upservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Categoria;
import com.estudo.upservice.domain.Servico;
import com.estudo.upservice.repository.CategoriaRepository;
import com.estudo.upservice.repository.ServicoRepository;
import com.estudo.upservice.service.exceptions.ObjectNotFoundException;

@Service
public class ServicoService {
    
    @Autowired
    private ServicoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Servico find(Integer id){
        Optional<Servico> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
            "Objeto não encontrado! Id: "+ id+", Tipo: "+Servico.class.getName()));
    }

    public Page<Servico> search(String nome, List<Integer>ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
    
}
