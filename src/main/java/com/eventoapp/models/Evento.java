package com.eventoapp.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

/**
 * @author leandro almeida
 * 
 *TODA CLASS TEM QUE COMEÇA COM LETRA MAIUSCULA 
 * 
 * 
 * */

@Entity
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO) // GERAR O ID AUTOMATICAMENTE 
	private long codigo;
	
	@NotEmpty
	private String nome;
	
	@NotEmpty
	private String local;
	
	@NotEmpty
	private String data;
	
	@NotEmpty
	private String horario;
	
	@OneToMany// CADA EVENTO VAI TER UMA LISTA DE CONVIDADO "UM PARA MUITOS"
	private List<Convidado> convidado; // MANTENDO A RELAÇÃO ENTRE A TABELA CONVIDADO E TABELA EVENTO
	
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	

	
}
