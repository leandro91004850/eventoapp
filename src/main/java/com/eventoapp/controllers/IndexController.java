package com.eventoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author leandro almeida
 * 
 * REPONSÁVEL POR FAZER O CONTROLLER DAS PAGINAS, APÓS UMA REQUISIÇÃO CONFORME INDEX
 * SERÁ RETORNADO A PAGINA INDEX CONFORME ABAIXO.
 * 
 * */

@Controller 
public class IndexController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	
	
}
