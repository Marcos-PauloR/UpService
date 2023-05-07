package com.estudo.upservice.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.domain.enums.TipoCliente;
import com.estudo.upservice.dto.ClienteNewDTIO;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.resources.exception.FieldMessage;
import com.estudo.upservice.service.validation.utils.BR;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTIO>{

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteInsert ann){
    }

    @Override
    public boolean isValid(ClienteNewDTIO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null){
            list.add(new FieldMessage("email", "Email já Cadastrado"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CpfOuCnpj", "CPF Invalido!"));
        }
        if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())){
            list.add(new FieldMessage("CpfOuCnpj", "CNPJ Invalido!"));
        }


        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
            .addPropertyNode(e.getFieldname()).addConstraintViolation();
        }
        
        return list.isEmpty();
    }
    
    
}
