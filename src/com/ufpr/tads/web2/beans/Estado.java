package com.ufpr.tads.web2.beans;

public class Estado {
	private int id;
	private String sigla;
	private String nome;
	
	public Estado() {
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigla() {
		return this.sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
