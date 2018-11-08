package com.ufpr.tads.web2.facade;

import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.dao.AtendimentoDAO;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.ProdutoDAO;

public class AtendimentoFacade {
	
	// Atendimento
	public static void inserirAtendimento(Atendimento a) {
        AtendimentoDAO.insertAtendimento(a);
    }
	public static Atendimento buscarAtendimento (int id) {
        return AtendimentoDAO.buscarAtendimentoPorId(id);
    }
	public static List<Atendimento> buscarTodosAtendimentos () {
        return AtendimentoDAO.buscarAtendimentos();
    }

	// Tipo atendimento
	public static TipoAtendimento buscarTipoAtendimento(int id) {
        return TipoAtendimentoDAO.buscarTipoAtendimentoPorId(id);
    }
	public static List<TipoAtendimento> buscarTodosTiposAtendimento() {
        return TipoAtendimentoDAO.buscarTiposAtendimento();
    }

	// Produto
	public static void inserirProduto(Produto p) {
		ProdutoDAO.insertProduto(p);
	}
	public static void alterarProduto(Produto p) {
		ProdutoDAO.alterarProduto(p);
	}
	public static Produto buscarProduto(int id) {
		return ProdutoDAO.buscarProdutoPorId(id);
	}
	public static List<Produto> buscarTodosProdutos() {
		return ProdutoDAO.buscarProdutos();
	}
	public static void excluirProduto(int id) {
		ProdutoDAO.removerProduto(id);
	}
	

}
