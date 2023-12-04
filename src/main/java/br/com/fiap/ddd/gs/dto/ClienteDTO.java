package br.com.fiap.ddd.gs.dto;

public class ClienteDTO {
	private String nome, telefone, email, cep;
	private int id, idade;
	
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
