package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.beans.Gerente;
import com.ufpr.tads.web2.beans.Funcionario;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.beans.TipoUsuario;
import com.ufpr.tads.web2.dao.TipoUsuarioDAO;
import java.util.List;

public class UsuarioFacade {

    // usuario
    public static void inserirUsuario(Usuario c) {
        UsuarioDAO.insertUsuario(c);
    }

    public static void alterarUsuario(Usuario c) {
        UsuarioDAO.alterarUsuario(c);
    }

    public static Usuario buscarUsuario(int id) {
        return UsuarioDAO.buscarUsuarioPorId(id);
    }

    public static List<Usuario> buscarTodosUsuarios() {
        return UsuarioDAO.buscarUsuarios();
    }

    public static void excluirUsuario(int id) {
        UsuarioDAO.removerUsuario(id);
    }

    // TipoUsuario
    // public static void inserirTipoUsuario(TipoUsuario c) {
    // TipoUsuarioDAO.insertTipoUsuario(c);
    // }

    // public static void alterarTipoUsuario(TipoUsuario c) {
    // TipoUsuarioDAO.alterarTipoUsuario(c);
    // }

    public static TipoUsuario buscarTipoUsuario(int id) {
        return TipoUsuarioDAO.buscarTipoUsuarioPorId(id);
    }

    public static List<TipoUsuario> buscarTodosTiposUsuarios() {
        return TipoUsuarioDAO.buscarTiposUsuario();
    }

    // public static void excluirTipoUsuario(int id) {
    // TipoUsuarioDAO.removerTipoUsuario(id);
    // }


    // tipos específicos
    //gerente 
    public static List<Gerente> buscarTodosGerentes() {
        return UsuarioDAO.buscarGerentes();
    }
    public static Gerente buscarGerente(int id) {
    	return UsuarioDAO.buscarGerentePorId(id);
    }
    
    // funcionario
    public static List<Funcionario> buscarTodosFuncionarios() {
        return UsuarioDAO.buscarFuncionarios();
    }
    public static Funcionario buscarFuncionario(int id) {
    	return UsuarioDAO.buscarFuncionarioPorId(id);
    }

    // cliente
    public static List<Cliente> buscarTodosClientes() {
        return UsuarioDAO.buscarClientes();
    }
    public static Cliente buscarCliente(int id) {
    	return UsuarioDAO.buscarClientePorId(id);
    }
}
