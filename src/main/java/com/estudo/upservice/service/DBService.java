package com.estudo.upservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudo.upservice.domain.Anuncio;
import com.estudo.upservice.domain.Categoria;
import com.estudo.upservice.domain.Cidade;
import com.estudo.upservice.domain.Cliente;
import com.estudo.upservice.domain.Endereco;
import com.estudo.upservice.domain.Estado;
import com.estudo.upservice.domain.Pagamento;
import com.estudo.upservice.domain.PagamentoComBoleto;
import com.estudo.upservice.domain.PagamentoComCartao;
import com.estudo.upservice.domain.Servico;
import com.estudo.upservice.domain.enums.EstadoPagamento;
import com.estudo.upservice.domain.enums.Perfil;
import com.estudo.upservice.domain.enums.TipoCliente;
import com.estudo.upservice.repository.AnuncioRepository;
import com.estudo.upservice.repository.CategoriaRepository;
import com.estudo.upservice.repository.CidadeRepository;
import com.estudo.upservice.repository.ClienteRepository;
import com.estudo.upservice.repository.EnderecoRepository;
import com.estudo.upservice.repository.EstadoRepository;
import com.estudo.upservice.repository.PagamentoRepository;
import com.estudo.upservice.repository.ServicoRepository;

@Service
public class DBService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public void instanciateDatabase() throws ParseException {
        Categoria cat1 = new Categoria(null, "Informatica");
        Categoria cat2 = new Categoria(null, "Escritorio");
        Categoria cat3 = new Categoria(null, "Cama mesa e banho");
        Categoria cat4 = new Categoria(null, "Eletronicos");
        Categoria cat5 = new Categoria(null, "Jardinagem");
        Categoria cat6 = new Categoria(null, "Decoração");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Servico prod1 = new Servico(null, "Computador", 2000.00);
        Servico prod2 = new Servico(null, "Impressora", 800.00);
        Servico prod3 = new Servico(null, "Mouse", 80.00);
        Servico prod4 = new Servico(null, "Mesa de Escritorio", 300.00);
        Servico prod5 = new Servico(null, "Toalha", 50.00);
        Servico prod6 = new Servico(null, "Colcha", 200.00);
        Servico prod7 = new Servico(null, "Tv true Color", 1200.00);
        Servico prod8 = new Servico(null, "Roçadera", 800.00);
        Servico prod9 = new Servico(null, "Abajour", 100.00);
        Servico prod10 = new Servico(null, "Pendente", 180.00);
        Servico prod11 = new Servico(null, "Shampoo", 90.00);
        Servico p12 = new Servico(null, "Servico 12", 10.00);
        Servico p13 = new Servico(null, "Servico 13", 10.00);
        Servico p14 = new Servico(null, "Servico 14", 10.00);
        Servico p15 = new Servico(null, "Servico 15", 10.00);
        Servico p16 = new Servico(null, "Servico 16", 10.00);
        Servico p17 = new Servico(null, "Servico 17", 10.00);
        Servico p18 = new Servico(null, "Servico 18", 10.00);
        Servico p19 = new Servico(null, "Servico 19", 10.00);
        Servico p20 = new Servico(null, "Servico 20", 10.00);
        Servico p21 = new Servico(null, "Servico 21", 10.00);
        Servico p22 = new Servico(null, "Servico 22", 10.00);
        Servico p23 = new Servico(null, "Servico 23", 10.00);
        Servico p24 = new Servico(null, "Servico 24", 10.00);
        Servico p25 = new Servico(null, "Servico 25", 10.00);
        Servico p26 = new Servico(null, "Servico 26", 10.00);
        Servico p27 = new Servico(null, "Servico 27", 10.00);
        Servico p28 = new Servico(null, "Servico 28", 10.00);
        Servico p29 = new Servico(null, "Servico 29", 10.00);
        Servico p30 = new Servico(null, "Servico 30", 10.00);
        Servico p31 = new Servico(null, "Servico 31", 10.00);
        Servico p32 = new Servico(null, "Servico 32", 10.00);
        Servico p33 = new Servico(null, "Servico 33", 10.00);
        Servico p34 = new Servico(null, "Servico 34", 10.00);
        Servico p35 = new Servico(null, "Servico 35", 10.00);
        Servico p36 = new Servico(null, "Servico 36", 10.00);
        Servico p37 = new Servico(null, "Servico 37", 10.00);
        Servico p38 = new Servico(null, "Servico 38", 10.00);
        Servico p39 = new Servico(null, "Servico 39", 10.00);
        Servico p40 = new Servico(null, "Servico 40", 10.00);
        Servico p41 = new Servico(null, "Servico 41", 10.00);
        Servico p42 = new Servico(null, "Servico 42", 10.00);
        Servico p43 = new Servico(null, "Servico 43", 10.00);
        Servico p44 = new Servico(null, "Servico 44", 10.00);
        Servico p45 = new Servico(null, "Servico 45", 10.00);
        Servico p46 = new Servico(null, "Servico 46", 10.00);
        Servico p47 = new Servico(null, "Servico 47", 10.00);
        Servico p48 = new Servico(null, "Servico 48", 10.00);
        Servico p49 = new Servico(null, "Servico 49", 10.00);
        Servico p50 = new Servico(null, "Servico 50", 10.00);

        cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
        cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
        cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
        cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
        cat5.getProdutos().addAll(Arrays.asList(prod8));
        cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
        cat7.getProdutos().addAll(Arrays.asList(prod11));

        prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
        prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
        prod4.getCategorias().addAll(Arrays.asList(cat2));
        prod5.getCategorias().addAll(Arrays.asList(cat3));
        prod6.getCategorias().addAll(Arrays.asList(cat3));
        prod7.getCategorias().addAll(Arrays.asList(cat4));
        prod8.getCategorias().addAll(Arrays.asList(cat5));
        prod9.getCategorias().addAll(Arrays.asList(cat6));
        prod10.getCategorias().addAll(Arrays.asList(cat6));
        prod11.getCategorias().addAll(Arrays.asList(cat7));

        cat1.getProdutos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        p12.getCategorias().add(cat1);
        p13.getCategorias().add(cat1);
        p14.getCategorias().add(cat1);
        p15.getCategorias().add(cat1);
        p16.getCategorias().add(cat1);
        p17.getCategorias().add(cat1);
        p18.getCategorias().add(cat1);
        p19.getCategorias().add(cat1);
        p20.getCategorias().add(cat1);
        p21.getCategorias().add(cat1);
        p22.getCategorias().add(cat1);
        p23.getCategorias().add(cat1);
        p24.getCategorias().add(cat1);
        p25.getCategorias().add(cat1);
        p26.getCategorias().add(cat1);
        p27.getCategorias().add(cat1);
        p28.getCategorias().add(cat1);
        p29.getCategorias().add(cat1);
        p30.getCategorias().add(cat1);
        p31.getCategorias().add(cat1);
        p32.getCategorias().add(cat1);
        p33.getCategorias().add(cat1);
        p34.getCategorias().add(cat1);
        p35.getCategorias().add(cat1);
        p36.getCategorias().add(cat1);
        p37.getCategorias().add(cat1);
        p38.getCategorias().add(cat1);
        p39.getCategorias().add(cat1);
        p40.getCategorias().add(cat1);
        p41.getCategorias().add(cat1);
        p42.getCategorias().add(cat1);
        p43.getCategorias().add(cat1);
        p44.getCategorias().add(cat1);
        p45.getCategorias().add(cat1);
        p46.getCategorias().add(cat1);
        p47.getCategorias().add(cat1);
        p48.getCategorias().add(cat1);
        p49.getCategorias().add(cat1);
        p50.getCategorias().add(cat1);

        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        servicoRepository
                .saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9,
                        prod10, prod11));

        servicoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        Cidade cid1 = new Cidade(null, "Uberlândia", est1);
        Cidade cid2 = new Cidade(null, "São Paulo", est2);
        Cidade cid3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().addAll(Arrays.asList(cid1));
        est2.getCidades().addAll(Arrays.asList(cid2, cid3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));

        Cliente cli1 = new Cliente(null, "Maria Silva", "daegon320@gmail.com", "12345678900",
                TipoCliente.PESSOAFISICA,
                pe.encode("123"));
        Cliente cli2 = new Cliente(null, "Ana Costa", "asdasd@gmail.com", "04241523048",
                TipoCliente.PESSOAFISICA,
                pe.encode("123"));
        cli2.addPerfil(Perfil.ADMIN);
        cli1.getTelefones().addAll(Arrays.asList("912345678", "987654321"));
        cli2.getTelefones().addAll(Arrays.asList("912345678", "643515316"));

        Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 341", "Jardim", "74000000", cli1, cid1);
        Endereco e2 = new Endereco(null, "Avenida Matos", "105", "sala 800", "centro", "74000000", cli1, cid2);
        Endereco e3 = new Endereco(null, "Avenida T63", "105", "sala 800", "marista", "74000000", cli2, cid2);

        cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepository.saveAll(Arrays.asList(e1, e2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Anuncio ped1 = new Anuncio(null, null, cli1, e1);
        Anuncio ped2 = new Anuncio(null, null, cli1, e2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);
        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
                sdf.parse("20/10/2023 12:00"),
                null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        anuncioRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

    }

}
