package com.estudo.upservice.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.dto.ClienteDTO;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.resources.exception.FieldMessage;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repo;

    @Override
    public void initialize(ClienteUpdate ann){
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
        Integer id = Integer.parseInt(map.get("id"));

        Cliente aux = repo.findByEmail(objDto.getEmail());
        if(aux != null &&  !aux.getId().equals(id) ){
            list.add(new FieldMessage("email", "Email já Cadastrado"));
        }


        for (FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
            .addPropertyNode(e.getFieldname()).addConstraintViolation();
        }
        
        return list.isEmpty();
    }
    
    
}
