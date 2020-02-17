package com.jda.app;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jda.app.domain.Cliente;
import com.jda.app.domain.ItemPedido;
import com.jda.app.domain.Pagamento;
import com.jda.app.domain.PagamentoComBoleto;
import com.jda.app.domain.PagamentoComCartao;
import com.jda.app.domain.Pedido;
import com.jda.app.domain.Produto;
import com.jda.app.domain.enums.EstadoPagamento;
import com.jda.app.repositories.ClienteRepository;
import com.jda.app.repositories.ItemPedidoRepository;
import com.jda.app.repositories.PagamentoRepository;
import com.jda.app.repositories.PedidoRepository;
import com.jda.app.repositories.ProdutoRepository;

@SpringBootApplication
public class AppVendasApiApplication implements CommandLineRunner {
	
	

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	

	public static void main(String[] args) {
		SpringApplication.run(AppVendasApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Cliente cli1 = new Cliente(null, "Maria Lucia", "maria@gmail.com", "36378912377");
		Cliente cli2 = new Cliente(null, "Jader de Andrade", "jaderdeandrade@gmail.com", "33999461726");
		
		Produto p1 = new Produto(null, "Computador", 2000.00,"Lenovo Core I5");
		Produto p2 = new Produto(null, "Impressora", 800.00,"Desk Jetom Epson");
		Produto p3 = new Produto(null, "Mouse", 80.00,"Mouse Optico Multilaser");

     	clienteRepository.saveAll(Arrays.asList(cli1,cli2));
     	produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
     	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), cli1);
		
     	Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	//Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco	
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));	
		 
	}

}
