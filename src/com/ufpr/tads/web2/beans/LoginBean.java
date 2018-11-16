package com.ufpr.tads.web2.beans;

public class LoginBean {
	private int id;
	private String nome;
	private String email;
	private int tipoUsuario;

	public LoginBean() {
	}
	
	public LoginBean(int id, String nome) {
		setId(id);
		setNome(nome);
	}
	
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
