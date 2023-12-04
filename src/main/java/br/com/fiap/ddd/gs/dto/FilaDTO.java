package br.com.fiap.ddd.gs.dto;

public class FilaDTO {
	private String senha;
	private String preferencial;
	private boolean isPreferencial;
	private int id;

	// Getters and Setters
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
		if (isPreferencial)
			this.preferencial = "Y";
		else
			this.preferencial = "N";

		this.isPreferencial = isPreferencial;
	}

	public int getId() {
		return id;
	}
}
