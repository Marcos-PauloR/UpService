package com.estudo.upservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.upservice.domain.Servico;
import com.estudo.upservice.dto.ProdutoDTO;
import com.estudo.upservice.resources.utils.URL;
import com.estudo.upservice.service.ServicoService;


@RestController
@RequestMapping(value="/servicos")
public class ServicoResource{

    @Autowired
    private ServicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id){

        Servico obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value="nome",defaultValue = "") String nome,
            @RequestParam(value="categorias",defaultValue = "") String categorias,  
            @RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "24") Integer linesPerPage, 
            @RequestParam(defaultValue = "nome") String orderBy, 
            @RequestParam(defaultValue = "ASC") String direction ){
        
        List<Integer> lista = URL.decodeIntList(categorias);
        String nomeDecoded = URL.decodeParam(nome);   
        Page<Servico> list = service.search(nomeDecoded, lista, page, linesPerPage, orderBy, direction);
        Page<ProdutoDTO > listDTO = list.map(obj -> new ProdutoDTO(obj));
        
        return ResponseEntity.ok().body(listDTO);
    }

}