package br.com.fiap.ddd.gs.domain;

import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class Hospital {

	private String nome, telefone, cep;
	private int id;
	
	public Hospital(String nome, String telefone, String cep, int id) {
		
		if(nome == null || nome.isBlank()) {
			throw new RegraDeNegocioException("O campo de nome está vazio");
		}
		
		if(telefone == null || telefone.isBlank()) {
			throw new RegraDeNegocioException("O campo de telefone está vazio");
		}
		
		if(cep == null || cep.isBlank()) {
			throw new RegraDeNegocioException("O campo de cep está vazio");
		}
		
		if(id <= 0) {
			throw new RegraDeNegocioException("O id não pode ser zero");
		}
		
		this.setId(id);
		this.setCep(cep);
		this.setNome(nome);
		this.setTelefone(telefone);
	}

	
	@Override
	public String toString() {
		return String.format("ID: %s | Nome: %s | Telefone: %s| Cep: %s", this.id, this.nome, this.telefone, this.cep);
	}
	
	//Getters and Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
