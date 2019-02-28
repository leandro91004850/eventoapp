package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	Iterable<Convidado> findByEvento(Evento evento); // REALIZANDO A BUSCA DOS CONVIDADOS CONFORME ESTÃO ISERIDOS E SEUS RESPECTIVOS EVENTOS
	Convidado findByRg(String rg);
}
