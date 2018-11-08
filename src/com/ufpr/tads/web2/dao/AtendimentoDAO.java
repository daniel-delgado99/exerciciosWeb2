package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;

public class AtendimentoDAO {
static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertAtendimento(Atendimento a) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_ateNdimento"
					+ "(dt_hr_atendimento, desc_atendimento, res_atendimento, id_produto, id_tipo_atendimento, id_usuario, id_cliente) "
					+ "values (?, ?, ?, ?, ?, ?, ?);");
			pst.setString(1, a.getDataHora());
			pst.setString(2, a.getDesc());
			pst.setString(3, a.getRes());
			pst.setInt(4, a.getProduto().getId());
			pst.setInt(5, a.getTipoAtendimento().getId());
			pst.setInt(6, a.getUsuario().getId());
			pst.setInt(7, a.getCliente().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Atendimento> buscarAtendimentos() {
		PreparedStatement pst;
		List<Atendimento> atendimentos= new ArrayList<Atendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_atendimento;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Atendimento a = new Atendimento();
				a.setId(rs.getInt("id_atendimento"));
				a.setDataHora(rs.getString("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setProduto(ProdutoDAO.buscarProdutoPorId(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(TipoAtendimentoDAO.buscarTipoAtendimentoPorId(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setUsuario(UsuarioDAO.buscarUsuarioPorId(Integer.parseInt(rs.getString("id_usuario"))));
				a.setCliente(ClienteDAO.buscarClientePorId(Integer.parseInt(rs.getString("id_cliente"))));
				
				atendimentos.add(a);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimentos;
	}
	
	public static Atendimento buscarAtendimentoPorId(int id) {
		PreparedStatement pst;
		Atendimento a = new Atendimento();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_atendimento WHERE id_atendimento=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				a.setId(rs.getInt("id_atendimento"));
				a.setDataHora(rs.getString("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setProduto(ProdutoDAO.buscarProdutoPorId(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(TipoAtendimentoDAO.buscarTipoAtendimentoPorId(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setUsuario(UsuarioDAO.buscarUsuarioPorId(Integer.parseInt(rs.getString("id_usuario"))));
				a.setCliente(ClienteDAO.buscarClientePorId(Integer.parseInt(rs.getString("id_cliente"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static void alterarAtendimento(Atendimento a) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_atendimento SET "
					+ "dt_hr_atendimento = ?, "
					+ "desc_atendimento = ?, "
					+ "res_atendimento = ?, "
					+ "id_produto = ?, "
					+ "id_tipo_atendimento = ?,"
					+ "id_usuario = ?,"
					+ "id_cliente = ?"
					+ "WHERE id_produto = ?;");
			pst.setString(1, a.getDataHora());
			pst.setString(2, a.getDesc());
			pst.setString(3, a.getRes());
			pst.setInt(4, a.getProduto().getId());
			pst.setInt(5, a.getTipoAtendimento().getId());
			pst.setInt(6, a.getUsuario().getId());
			pst.setInt(7, a.getCliente().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removerAtendimento(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM tb_atendimento WHERE id_atendimento = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
