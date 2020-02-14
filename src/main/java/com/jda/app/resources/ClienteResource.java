package com.jda.app.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "O REST está funcionando"; 
	}

}
