package com.ufpr.tads.web2.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Atendimento {
	private int id;
	private Date dataHora;
	private String desc;
	private String res;
	private Produto produto;
	private TipoAtendimento tipoAtendimento;
	private Cliente cliente;
	private String solucao;

	public Atendimento() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoAtendimento getTipoAtendimento() {
		return tipoAtendimento;
	}

	public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
		this.tipoAtendimento = tipoAtendimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getSolucao() {
		return solucao;
	}
	
	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}
}
