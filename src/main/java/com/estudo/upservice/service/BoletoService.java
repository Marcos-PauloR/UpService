package com.estudo.upservice.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.PagamentoComBoleto;

@Service
public class BoletoService {
    
    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date intanteDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(intanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }



}
