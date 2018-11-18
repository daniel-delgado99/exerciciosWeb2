package com.ufpr.tads.web2.facade;

import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.dao.AtendimentoDAO;

import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.TipoAtendimentoDAO;

public class AtendimentoFacade {

	// Atendimento
	public static void inserirAtendimento(Atendimento a) {
		AtendimentoDAO.insertAtendimento(a);
	}

	public static Atendimento buscarAtendimento(int id) {
		return AtendimentoDAO.buscarAtendimentoPorId(id);
	}

	public static List<Atendimento> buscarTodosAtendimentos() {
		return AtendimentoDAO.buscarAtendimentos();
	}
	
	public static List<Atendimento> buscarAtendimentosPorCliente(int id) {
		return AtendimentoDAO.buscarAtendimentosPorClienteId(id);
	}
	
	public static List<Atendimento> buscarAtendimentosEmAberto() {
		return AtendimentoDAO.buscarAtendimentosEmAberto();
	}
	
	public static void alterarAtendimento(Atendimento a) {
		AtendimentoDAO.alterarAtendimento(a);
	}
	
	public static void removerAtendimento(int id) {
		AtendimentoDAO.removerAtendimento(id);
	}

	// Tipo atendimento
	public static TipoAtendimento buscarTipoAtendimento(int id) {
		return TipoAtendimentoDAO.buscarTipoAtendimentoPorId(id);
	}

	public static List<TipoAtendimento> buscarTodosTiposAtendimento() {
		return TipoAtendimentoDAO.buscarTiposAtendimento();
	}
}
