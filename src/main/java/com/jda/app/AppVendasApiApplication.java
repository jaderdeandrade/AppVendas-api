package com.jda.app;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jda.app.domain.Cliente;
import com.jda.app.repositories.ClienteRepository;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class AppVendasApiApplication implements CommandLineRunner {
	
	
	@Autowired
	private ClienteRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppVendasApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		 Cliente c1 = new Cliente(null, "Maria Brown", "maria@gmail.com", "988888888");
		 Cliente c2 = new Cliente(null, "Alex Green", "alex@gmail.com", "977777777");

		categoriaRepository.saveAll(Arrays.asList(c1, c2));
	}



}
