package br.com.fiap.ddd.gs.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class Atendimento {
	
	private String tipo, descricao, status, servico;
	private int id;
	private LocalDateTime dataAtual = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String dtAtendimento = dataAtual.format(formatter);
	private boolean agendado;
	
	public Atendimento(String tipo, String descricao, boolean agendado, String servico, int id) {
		
		this.setAgendado(agendado);

		validacao(tipo, servico, id);
		
		this.setDescricao(descricao);
		this.setId(id);
		this.setServico(servico);
		this.setTipo(tipo);
	}
	
	public Atendimento(String tipo, String descricao, String status, String servico, int id) {
		
		validacao(tipo, servico, id);
		
		this.setId(id);
		this.setServico(servico);
		this.setTipo(tipo);
		this.setStatus(status);
	}

	public void validacao(String tipo, String servico, int id) {
		if(tipo == null || tipo.isBlank()) {
			throw new RegraDeNegocioException("O campo de tipo está vazio");
		}
		
		if(servico == null || servico.isBlank()) {
			throw new RegraDeNegocioException("O campo de servico está vazio");
		}
		
		if(id <= 0) {
			throw new RegraDeNegocioException("O id não pode ser zero");
		}
	}
	
	@Override
	public String toString() {
		return String.format("Id: %s | Tipo: %s | Descricao: %s | Servico: %s | Status: %s | Data: %s", this.id, this.tipo, this.descricao, this.servico, this.status, this.dtAtendimento);
	}

	//Getters and Setters
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	
	public String getDtAtendimento() {
		return dtAtendimento;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public boolean isAgendado() {
		return agendado;
	}

	public void setAgendado(boolean agendado) {
		if(agendado) this.setStatus("A");
		else this.status = "F";
		
		this.agendado = agendado;
	}
	
	
}
