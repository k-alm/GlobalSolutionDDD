package br.com.fiap.ddd.gs.domain;

import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class Cliente {
	private String nome, telefone, email, cep;
	private int id, idade;
	
	public Cliente(String nome, String telefone, String email, String cep, int idade, int id) {
		validacao(nome, telefone, email, cep);
		
		if(id <= 0) {
			throw new RegraDeNegocioException("Id não pode ser menor que zero");
		}
		
		this.setNome(nome);
		this.setEmail(email);
		this.setTelefone(telefone);
		this.setCep(cep);
		this.setIdade(idade);
		this.setId(id);
	}

	
	public void validacao(String nome, String telefone, String email, String cep) {
		if(nome == null || nome.isBlank()) {
			throw new RegraDeNegocioException("O campo de nome está vazio");
		}
		
		if(email == null || email.isBlank()) {
			throw new RegraDeNegocioException("O campo de email está vazio");
		}
		
		if(!email.contains("@")) {
			throw new RegraDeNegocioException("Email inválido");	
		}
		
		if(telefone == null || telefone.isBlank()) {
			throw new RegraDeNegocioException("O campo de telefone está vazio");
		}
		
		if(cep == null || cep.isBlank()) {
			throw new RegraDeNegocioException("O campo de cep está vazio");
		}
		
		if(idade < 0) {
			throw new RegraDeNegocioException("Idade inválida");
		}
	}

	@Override
	public String toString() {
		return String.format("Nome: %s email: %s | telefone: %s | cep: %s", this.nome, this.email, this.telefone, this.cep);
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	
}
