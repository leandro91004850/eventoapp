package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.models.Evento;

/**
 * @author leandro almeida
 * 
 * 
 * 
 * */
public interface EventoRepository extends CrudRepository<Evento, String>{
	
	// FAZENDO A BUSCA NO BANCO DE DADOS UMA BUSCA ESPECÍFICA
	Evento findByCodigo(long codigo);

}
