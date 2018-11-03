package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;
import java.util.List;

public class ClientesFacade {
	public static void inserir(Cliente c) {
        ClienteDAO.insertCliente(c);
    }
	public static void alterar(Cliente c) {
		ClienteDAO.alterarCliente(c);
    }
	public static Cliente buscar(int id) {
        return ClienteDAO.buscarClientePorId(id);
    }
	public static List<Cliente> buscarTodos() {
        return ClienteDAO.buscarClientes();
    }
	public static void excluir(int id) {
        ClienteDAO.removerCliente(id);
    }
}
