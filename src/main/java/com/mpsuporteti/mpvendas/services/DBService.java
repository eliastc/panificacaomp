package com.mpsuporteti.mpvendas.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import com.mpsuporteti.mpvendas.MpvendasApplication;
import com.mpsuporteti.mpvendas.domain.Categoria;
import com.mpsuporteti.mpvendas.domain.Cidade;
import com.mpsuporteti.mpvendas.domain.Cliente;
import com.mpsuporteti.mpvendas.domain.Endereco;
import com.mpsuporteti.mpvendas.domain.Estado;
import com.mpsuporteti.mpvendas.domain.ItemPedido;
import com.mpsuporteti.mpvendas.domain.Pagamento;
import com.mpsuporteti.mpvendas.domain.PagamentoComBoleto;
import com.mpsuporteti.mpvendas.domain.PagamentoComCartao;
import com.mpsuporteti.mpvendas.domain.Pedido;
import com.mpsuporteti.mpvendas.domain.Produto;
import com.mpsuporteti.mpvendas.domain.enums.EstadoPagamento;
import com.mpsuporteti.mpvendas.domain.enums.Perfil;
import com.mpsuporteti.mpvendas.domain.enums.TipoCliente;
import com.mpsuporteti.mpvendas.repositories.CategoriaRepository;
import com.mpsuporteti.mpvendas.repositories.CidadeRepository;
import com.mpsuporteti.mpvendas.repositories.ClienteRepository;
import com.mpsuporteti.mpvendas.repositories.EnderecoRepository;
import com.mpsuporteti.mpvendas.repositories.EstadoRepository;
import com.mpsuporteti.mpvendas.repositories.ItemPedidoRepository;
import com.mpsuporteti.mpvendas.repositories.PagamentoRepository;
import com.mpsuporteti.mpvendas.repositories.PedidoRepository;
import com.mpsuporteti.mpvendas.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MpvendasApplication.class, args);
	}
	
	
	public void instantiateTestDatabase() throws ParseException {
		
		
		Categoria cat1 = new Categoria(null, "Pães");
		Categoria cat2 = new Categoria(null, "Bolachas");
		Categoria cat3 = new Categoria(null, "Frios");	
		
		
		Produto p1 = new Produto(null, "Pão Bolachão", 9.90);
		Produto p2 = new Produto(null, "Pão Francês", 9.90);
		Produto p3 = new Produto(null, "Pão Doce", 7.80);
		Produto p4 = new Produto(null, "Pão Baguete", 4.10);
		Produto p5 = new Produto(null, "Bolacha Maria", 2.99);
		Produto p6 = new Produto(null, "Bolacha Maizena", 3.15);
		Produto p7= new Produto(null, "Queijo Prato", 33.15);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4));
		cat2.getProdutos().addAll(Arrays.asList(p5, p3));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat3));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		p4.getCategorias().addAll(Arrays.asList(cat1));
		p5.getCategorias().addAll(Arrays.asList(cat2));
		p6.getCategorias().addAll(Arrays.asList(cat2));
		p7.getCategorias().addAll(Arrays.asList(cat3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
		
		
		Estado est1 = new Estado(null, "Pernambuco");
		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "Olinda", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		
		estadoRepository.saveAll(Arrays.asList(est1));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Cliente cli1 = new Cliente(null, "Elias Teotonio", "eliascalado@hotmail.com", "36378912377", TipoCliente.PESSOAFISICA, "123");	
		cli1.getTelefones().addAll(Arrays.asList("3446.5893", "3033.3632", "98824.3638"));
		cli1.addPerfil(Perfil.ADMIN);
		
		Endereco e1 = new Endereco(null, "Rua Souza Bandeira" , "211", "null", "Cordeiro", "50011-150", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Padre Leonardo Greco" , "129", "null", "Cordeiro", "50720-670", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));		
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 11:57"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("12/12/2020 10:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5); 
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/12/2020 13:00"), null); 
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 9.90);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 7.80);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 0.90, 1, 9.90);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
