package com.estudo.upservice.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Anuncio;
import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.domain.PagamentoComBoleto;
import com.estudo.upservice.domain.enums.EstadoPagamento;
import com.estudo.upservice.repository.AnuncioRepository;
import com.estudo.upservice.repository.PagamentoRepository;
import com.estudo.upservice.security.UserSS;
import com.estudo.upservice.service.exceptions.AuthorizationException;
import com.estudo.upservice.service.exceptions.ObjectNotFoundException;

@Service
public class AnuncioService {
    
    @Autowired
    private AnuncioRepository repository;

    @Autowired
    private BoletoService boletoService; 

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ClienteService clienteservice;


    public Anuncio find(Integer id){
        Optional<Anuncio> obj = repository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException(
            "Objeto não encontrado! Id: "+ id+", Tipo: "+Anuncio.class.getName()));
    }

    public Anuncio insert(Anuncio obj){
        obj.setId(null);
        //TODO Criar validação de nota media
        obj.setNotaMedia(0.1);
        obj.setCliente(clienteservice.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setAnuncio(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, new Date());    
        }
        obj = repository.save(obj);

        pagamentoRepository.save(obj.getPagamento());
        return obj;
    }


    public Page<Anuncio> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        UserSS user = UserService.authenticated();
        if(user ==null){
            throw new AuthorizationException("Acesso Negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteservice.find(user.getId());

        return repository.findByCliente(cliente, pageRequest);
    }
}