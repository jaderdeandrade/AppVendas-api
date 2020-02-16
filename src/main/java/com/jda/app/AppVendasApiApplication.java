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

		Cliente c1 = new Cliente(null, "Maria Brown", "maria@gmail.com", "988888888");
		Cliente c2 = new Cliente(null, "Alex Green", "alex@gmail.com", "977777777");
		
		Produto p1 = new Produto(null, "Computador", 2000.00,"Lenovo Core I5");
		Produto p2 = new Produto(null, "Impressora", 800.00, "Ink Jet Epson A4");
		Produto p3 = new Produto(null, "Mouse", 80.00,"Mouse Optico Multilaser");

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		clienteRepository.saveAll(Arrays.asList(c1, c2));

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), c1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), c1);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		c1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		/*
		 * p1.getItens().addAll(Arrays.asList(ip1));
		 * p2.getItens().addAll(Arrays.asList(ip3));
		 * p3.getItens().addAll(Arrays.asList(ip2));
		 */

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
