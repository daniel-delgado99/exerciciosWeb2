package com.ufpr.tads.web2.beans;

public class Produto {
	private int id;
	private String nome;
	
	public Produto() {
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}