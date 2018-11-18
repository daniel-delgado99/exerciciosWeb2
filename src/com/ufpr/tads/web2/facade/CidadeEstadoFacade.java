package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.dao.CidadeDAO;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.dao.EstadoDAO;
import java.util.List;

public class CidadeEstadoFacade {

    // cidade
    // public static void inserirCidade(Cidade c) {
    //     CidadeDAO.insertCidade(c);
    // }

    // public static void alterarCidade(Cidade c) {
    //     CidadeDAO.alterarCidade(c);
    // }

    public static Cidade buscarCidade(int id) {
        return CidadeDAO.buscarCidadePorId(id);
    }

    public static List<Cidade> buscarTodosCidades() {
        return CidadeDAO.buscarCidades();
    }
    
    public static List<Cidade> buscarCidadesPorEstado(int estadoId) {
    	return CidadeDAO.buscarCidadesPorEstado(estadoId);
    }

    // public static void excluirCidade(int id) {
    //     CidadeDAO.removerCidade(id);
    // }

    // estado
    // public static void inserirEstado(Estado c) {
    //     EstadoDAO.insertEstado(c);
    // }

    // public static void alterarEstado(Estado c) {
    //     EstadoDAO.alterarEstado(c);
    // }

    public static Estado buscarEstado(int id) {
        return EstadoDAO.buscarEstadoPorId(id);
    }

    public static List<Estado> buscarTodosEstados() {
        return EstadoDAO.buscarEstados();
    }

    // public static void excluirEstado(int id) {
    //     EstadoDAO.removerEstado(id);
    // }
}
