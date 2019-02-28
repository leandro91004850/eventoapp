package com.eventoapp.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

/**
 * @author leandro almeida
 * 
 * 
 * 
 * 
 * */


@Controller
public class EventoController {
	
	@Autowired  // INJEÇÃO DE INDEPENDENCIA 
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	
	@RequestMapping("evento/dataCollection")
	public String DataCollection () {
		return "evento/dataCollection";
		
	}

	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {		
		return "evento/formEvento";
	}
	
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes) {	//SALVAR O EVENTO NO BANCO DE DADOS 
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "verifique os campos");
			return "redirect:/cadastrarEvento";
		}
		er.save(evento);  
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso");
		return "redirect:/cadastrarEvento";
	}
	
	
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index"); // PAGINA QUE VAI SER REDERINZADA DE ACORDO COM OS DADOS DE EVENTO 
		Iterable<Evento> eventos = er.findAll(); // PORQUE É UMA LISTA DE EVENTOS, E IRÁ BUSCA TODO OS EVENTOS
		mv.addObject("eventos", eventos);
		return mv;
	
	}
	
	
	@RequestMapping("/listaEvento")
	public ModelAndView listagemEventos() {
		ModelAndView mv = new ModelAndView("listagem"); // PAGINA QUE VAI SER REDERINZADA DE ACORDO COM OS DADOS DE EVENTO 
		Iterable<Evento> eventos = er.findAll(); // PORQUE É UMA LISTA DE EVENTOS, E IRÁ BUSCA TODO OS EVENTOS
		mv.addObject("eventos", eventos);
		return mv;
	
	}
	
	
	// METODO QUE VAI RECEBER A REQUISIÇÃO VIA MÉTODO POST  E SALVAR NO BANCO DE DADOS
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)// VAI REDIRECIONAR PARA O RESPECTIVO CÓDIGO QUE FOI CRIADO AO REALIZAR O CLICK
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		// FAZENDO A BUSCA NO BANCO DE DADOS UMA BUSCA ESPECÍFICA
		Evento evento = er.findByCodigo(codigo); //USANDO O CÓDIGO EXPECÍFICO CRIADO "FAZENDO A BUSCA"
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		mv.addObject("evento", evento);
		//System.out.println("evento" + evento);
		
		Iterable<Convidado> convidados = cr.findByEvento(evento); //PEGANDO OS ELEMENTOS DOS RESPECTIVOS EVENTOS
		mv.addObject("convidados", convidados); // ENVIANDO A LISTA
		
		return mv;
	}
	
	
	// MÉTODO PARA DELETAMOS ELEMENTOS DENTRO DA NOSSA BASE 
	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo) {
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		return "redirect:/eventos";
	}
	
	
	// METODO QUE VAI RECEBER A REQUISIÇÃO VIA MÉTODO POST  E SALVAR NO BANCO DE DADOS
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)// VAI REDIRECIONAR PARA O RESPECTIVO CÓDIGO QUE FOI CRIADO AO REALIZAR O CLICK
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado,  BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "verifique os campos!");
			return "redirect:/{codigo}"; // SE TIVER ALGUM ERRO SERÁ REDIRECIONADO PARA A PAGINA NOVAMENTE
		}
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionando com sucesso");
		return "redirect:/{codigo}";
		
	}
	
	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg) {  
		Convidado convidado = cr.findByRg(rg); // PARA PODEMOS DELETAR ATRAVES DO RG TEMOS QUE CRIAR UMA BUSCA ESPECIFICA DENTRO DO CONVIDADO REPOSITORY
		cr.delete(convidado);
		
		Evento evento = convidado.getEvento();
		long codigoLong = evento.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/"+ codigo;
	}

	// ÚLTIMA AULLA 14
	
	
	
}
