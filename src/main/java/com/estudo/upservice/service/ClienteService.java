package com.estudo.upservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudo.upservice.domain.Cidade;
import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.domain.Endereco;
import com.estudo.upservice.domain.enums.Perfil;
import com.estudo.upservice.domain.enums.TipoCliente;
import com.estudo.upservice.dto.ClienteDTO;
import com.estudo.upservice.dto.ClienteNewDTIO;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.repository.EnderecoRepository;
import com.estudo.upservice.security.UserSS;
import com.estudo.upservice.service.exceptions.AuthorizationException;
import com.estudo.upservice.service.exceptions.DataIntegrityException;
import com.estudo.upservice.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;


    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private int size;

    public Cliente find(Integer id) {
        UserSS user = UserService.authenticated();
        if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())){
            throw new AuthorizationException("Acesso negado");
        }

        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir Cliente que possui Pedidos");
        }

    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findByEmail(String email){
        UserSS user = UserService.authenticated();
        if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())){
            throw new AuthorizationException("Acesso negado");
        }
        Cliente obj = repository.findByEmail(email);
        if(obj ==null){
            throw new ObjectNotFoundException("Objeto não encontrado! Id: "+user.getId()
                    +", Tipo: "+Cliente.class.getName());   
        }
        return obj;
    }   


    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
    }

    public Cliente fromDTO(ClienteNewDTIO objDTO){
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(),objDTO.getCpfOuCnpj() , TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()) );
        Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null , objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if(objDTO.getTelefone2()!=null) {
            cli.getTelefones().add(objDTO.getTelefone2());
        }if(objDTO.getTelefone3()!= null){
            cli.getTelefones().add(objDTO.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }



}
