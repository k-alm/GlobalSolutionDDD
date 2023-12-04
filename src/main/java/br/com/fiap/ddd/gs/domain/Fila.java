package br.com.fiap.ddd.gs.domain;

import java.util.Random;

import br.com.fiap.ddd.gs.exceptions.RegraDeNegocioException;

public class Fila {
	private String senha;
	private String preferencial;
	private boolean isPreferencial;
	private int id;
	
	
	public Fila(boolean preferencial) {
		this.setSenha(gerarSenha());
		this.setPreferencial(preferencial);
	}

	
	public Fila(int id, String senha) {
		if(senha == null || senha.isBlank()) {
			throw new RegraDeNegocioException("O campo de senha está vazio");
		}
		
		if(id <= 0) {
			throw new RegraDeNegocioException("O Id não pode ser zero");
		}
		
		this.setId(id);
		this.setSenha(senha);
	}


	public String gerarSenha() {
		Random rand = new Random();
		StringBuilder senha = new StringBuilder();
		
		for(int i = 0; i < 3; i++) {
			char letra = (char) (rand.nextInt(26) + 'A');
			senha.append(letra);
		}
		
		for(int i = 0; i < 4; i++) {
			int numero =  rand.nextInt(9);
			senha.append(numero);
		}
		
		return senha.toString();
	}
	
	@Override
	public String toString() {
		return String.format("Id: %s | Senha: %s", id, senha);
	}
	
	//Getters and Setters
	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getPreferencial() {
		return preferencial;
	}


	public boolean isPreferencial() {
		return isPreferencial;
	}


	public void setPreferencial(boolean isPreferencial) {
		if(isPreferencial) this.preferencial = "Y";
		else this.preferencial = "N";
		
		this.isPreferencial = isPreferencial;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
}
