package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;

import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Funcionario;
import com.ufpr.tads.web2.beans.Gerente;
import com.ufpr.tads.web2.facade.CidadeEstadoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;
import com.ufpr.tads.web2.servlets.Criptografia;

public class UsuarioDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static void insertUsuario(Usuario u) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(
					"INSERT INTO tb_usuario("
						+ "cpf_usuario, nome_usuario, email_usuario, senha_usuario, data_usuario, "
						+ "rua_usuario, nr_usuario, cep_usuario, id_cidade, id_tipo_usuario"
					+ ") "
					+ "values (?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, u.getCpf());
			pst.setString(2, u.getNome());
			pst.setString(3, u.getEmail());
			pst.setString(4, Criptografia.criptografar(u.getSenha()));
			pst.setDate(5, new java.sql.Date(u.getData().getTime()));
			pst.setString(6, u.getRua());
			pst.setInt(7, u.getNr());
			pst.setString(8, u.getCep());
			pst.setInt(9, u.getCidade().getId());
			pst.setInt(10, u.getTipoUsuario().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Usuario buscarUsuarioPorId(int id) {
		PreparedStatement pst;
		Usuario u = new Usuario();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_usuario=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				u.setId(rs.getInt("id_usuario"));
				u.setCpf(rs.getString("cpf_usuario"));
				u.setNome(rs.getString("nome_usuario"));
				u.setEmail(rs.getString("email_usuario"));
				u.setSenha(rs.getString("senha_usuario"));
				u.setData(rs.getTimestamp("data_usuario"));
				u.setRua(rs.getString("rua_usuario"));
				u.setNr(rs.getInt("nr_usuario"));
				u.setCep(rs.getString("cep_usuario"));
				u.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				u.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public static List<Usuario> buscarUsuarios() {
		PreparedStatement pst;
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt("id_usuario"));
				u.setCpf(rs.getString("cpf_usuario"));
				u.setNome(rs.getString("nome_usuario"));
				u.setEmail(rs.getString("email_usuario"));
				u.setSenha(rs.getString("senha_usuario"));
				u.setData(rs.getTimestamp("data_usuario"));
				u.setRua(rs.getString("rua_usuario"));
				u.setNr(rs.getInt("nr_usuario"));
				u.setCep(rs.getString("cep_usuario"));
				u.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				u.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));

				usuarios.add(u);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public static void alterarUsuario(Usuario u) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_usuario SET nome_usuario = ?, data_usuario = ?, rua_usuario = ?, "
					+ "nr_usuario = ?,  cep_usuario = ?, id_cidade = ?,  id_tipo_usuario = ? WHERE id_usuario = ?;");
			pst.setString(1, u.getNome());
			pst.setDate(2, new java.sql.Date(u.getData().getTime()));
			pst.setString(3, u.getRua());
			pst.setInt(4, u.getNr());
			pst.setString(5, u.getCep());
			pst.setInt(6, u.getCidade().getId());
			pst.setInt(7, u.getTipoUsuario().getId());
			pst.setInt(8, u.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void removerUsuario(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM tb_usuario WHERE id_usuario = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Cliente> buscarClientes() {
		PreparedStatement pst;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_tipo_usuario = 1;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id_usuario"));
				c.setCpf(rs.getString("cpf_usuario"));
				c.setNome(rs.getString("nome_usuario"));
				c.setEmail(rs.getString("email_usuario"));
				c.setSenha(rs.getString("senha_usuario"));
				c.setData(rs.getTimestamp("data_usuario"));
				c.setRua(rs.getString("rua_usuario"));
				c.setNr(rs.getInt("nr_usuario"));
				c.setCep(rs.getString("cep_usuario"));
				c.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				c.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));

				clientes.add(c);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	public static Cliente buscarClientePorId(int id) {
		PreparedStatement pst;
		Cliente c = new Cliente();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_usuario=? AND id_tipo_usuario=1;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				c.setId(rs.getInt("id_usuario"));
				c.setCpf(rs.getString("cpf_usuario"));
				c.setNome(rs.getString("nome_usuario"));
				c.setEmail(rs.getString("email_usuario"));
				c.setSenha(rs.getString("senha_usuario"));
				c.setData(rs.getTimestamp("data_usuario"));
				c.setRua(rs.getString("rua_usuario"));
				c.setNr(rs.getInt("nr_usuario"));
				c.setCep(rs.getString("cep_usuario"));
				c.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				c.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	public static List<Funcionario> buscarFuncionarios() {
		PreparedStatement pst;
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_tipo_usuario = 2;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Funcionario f = new Funcionario();
				f.setId(rs.getInt("id_usuario"));
				f.setCpf(rs.getString("cpf_usuario"));
				f.setNome(rs.getString("nome_usuario"));
				f.setEmail(rs.getString("email_usuario"));
				f.setSenha(rs.getString("senha_usuario"));
				f.setData(rs.getTimestamp("data_usuario"));
				f.setRua(rs.getString("rua_usuario"));
				f.setNr(rs.getInt("nr_usuario"));
				f.setCep(rs.getString("cep_usuario"));
				f.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				f.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));

				funcionarios.add(f);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionarios;
	}
	
	public static Funcionario buscarFuncionarioPorId(int id) {
		PreparedStatement pst;
		Funcionario f = new Funcionario();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_usuario=? AND id_tipo_usuario=2;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				f.setId(rs.getInt("id_usuario"));
				f.setCpf(rs.getString("cpf_usuario"));
				f.setNome(rs.getString("nome_usuario"));
				f.setEmail(rs.getString("email_usuario"));
				f.setSenha(rs.getString("senha_usuario"));
				f.setData(rs.getTimestamp("data_usuario"));
				f.setRua(rs.getString("rua_usuario"));
				f.setNr(rs.getInt("nr_usuario"));
				f.setCep(rs.getString("cep_usuario"));
				f.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				f.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return f;
	}

	public static List<Gerente> buscarGerentes() {
		PreparedStatement pst;
		List<Gerente> gerentes = new ArrayList<Gerente>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_tipo_usuario = 3;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Gerente g = new Gerente();
				g.setId(rs.getInt("id_usuario"));
				g.setCpf(rs.getString("cpf_usuario"));
				g.setNome(rs.getString("nome_usuario"));
				g.setEmail(rs.getString("email_usuario"));
				g.setSenha(rs.getString("senha_usuario"));
				g.setData(rs.getTimestamp("data_usuario"));
				g.setRua(rs.getString("rua_usuario"));
				g.setNr(rs.getInt("nr_usuario"));
				g.setCep(rs.getString("cep_usuario"));
				g.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				g.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));

				gerentes.add(g);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gerentes;
	}
	
	public static Gerente buscarGerentePorId(int id) {
		PreparedStatement pst;
		Gerente g = new Gerente();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_usuario WHERE id_usuario=? AND id_tipo_usuario=3;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				g.setId(rs.getInt("id_usuario"));
				g.setCpf(rs.getString("cpf_usuario"));
				g.setNome(rs.getString("nome_usuario"));
				g.setEmail(rs.getString("email_usuario"));
				g.setSenha(rs.getString("senha_usuario"));
				g.setData(rs.getTimestamp("data_usuario"));
				g.setRua(rs.getString("rua_usuario"));
				g.setNr(rs.getInt("nr_usuario"));
				g.setCep(rs.getString("cep_usuario"));
				g.setCidade(CidadeEstadoFacade.buscarCidade(rs.getInt("id_cidade")));
				g.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(rs.getInt("id_tipo_usuario")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return g;
	}
}
