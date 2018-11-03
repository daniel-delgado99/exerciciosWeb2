package com.ufpr.tads.web2.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ufpr.tads.web2.beans.Cliente;

public class ClienteDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertCliente(Cliente c) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_cliente(cpf_cliente, nome_cliente, email_cliente, data_cliente, rua_cliente, nr_cliente, cep_cliente, cidade_cliente, uf_cliente) values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, c.getCpf());
			pst.setString(2, c.getNome());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getData());
			pst.setString(5, c.getRua());
			pst.setInt(6, c.getNr());
			pst.setString(7, c.getCep());
			pst.setString(8, c.getCidade());
			pst.setString(9, c.getUf());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Cliente> buscarClientes() {
		PreparedStatement pst;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_cliente;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("id_cliente"));
				c.setCpf(rs.getString("cpf_cliente"));
				c.setNome(rs.getString("nome_cliente"));
				c.setEmail(rs.getString("email_cliente"));
				c.setData(rs.getString("data_cliente"));
				c.setRua(rs.getString("rua_cliente"));
				c.setNr(rs.getInt("nr_cliente"));
				c.setCep(rs.getString("cep_cliente"));
				c.setUf(rs.getString("uf_cliente"));
				c.setCidade(rs.getString("cidade_cliente"));
				
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
			pst = con.prepareStatement("SELECT * FROM tb_cliente WHERE id_cliente=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				c.setId(rs.getInt("id_cliente"));
				c.setCpf(rs.getString("cpf_cliente"));
				c.setNome(rs.getString("nome_cliente"));
				c.setEmail(rs.getString("email_cliente"));
				c.setData(rs.getString("data_cliente"));
				c.setRua(rs.getString("rua_cliente"));
				c.setNr(rs.getInt("nr_cliente"));
				c.setCep(rs.getString("cep_cliente"));
				c.setUf(rs.getString("uf_cliente"));
				c.setCidade(rs.getString("cidade_cliente"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void alterarCliente(Cliente c) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_cliente SET "
					+ "cpf_cliente = ?,"
					+ "nome_cliente = ?, "
					+ "email_cliente = ?, "
					+ "data_cliente = ?, "
					+ "rua_cliente = ?, "
					+ "nr_cliente = ?, "
					+ "cep_cliente = ?, "
					+ " cidade_cliente = ?, "
					+ "uf_cliente = ?"
					+ "WHERE id_cliente = ?;");
			pst.setString(1, c.getCpf());
			pst.setString(2, c.getNome());
			pst.setString(3, c.getEmail());
			pst.setString(4, c.getData());
			pst.setString(5, c.getRua());
			pst.setInt(6, c.getNr());
			pst.setString(7, c.getCep());
			pst.setString(8, c.getCidade());
			pst.setString(9, c.getUf());
			pst.setInt(10, c.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removerCliente(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM tb_cliente WHERE id_cliente = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
