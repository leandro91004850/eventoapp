package com.eventoapp.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity // ENTIDADE, UMA TABELA DO BANCO DE DADOS 
public class Convidado {
	
	@Id
	@NotEmpty // NÃO RECEBERA VALORES VAZIOS
	private String rg;
	
	@NotEmpty 
	private String nomeConvidado;

	@ManyToOne // MUITOS CONVIDADOS PARA SÓ UM EVENTO "MUITAS PARA UM"
	private Evento evento;// MONTANDO A RELAÇÃO ENTRE A ENTIDADE "CONVIDADO" E A ENTIDADE "EVENTO"
	
	
	public String getRg() {
		return rg;	
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
	
}
