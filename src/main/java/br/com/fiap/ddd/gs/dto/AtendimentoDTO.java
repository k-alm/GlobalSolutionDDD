package br.com.fiap.ddd.gs.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AtendimentoDTO {
	private String tipo, descricao, status, servico;
	private int id;
	private LocalDateTime dataAtual = LocalDateTime.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private String dtAtendimento = dataAtual.format(formatter);
	private boolean agendado;

	// Getters and Setters
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
		if (agendado)
			this.setStatus("A");
		else
			this.status = "F";

		this.agendado = agendado;
	}
}
