package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;

public class AtendimentoDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertAtendimento(Atendimento a) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_atendimento"
					+ "(dt_hr_atendimento, desc_atendimento, res_atendimento, id_produto, id_tipo_atendimento, id_cliente) "
					+ "values (?, ?, ?, ?, ?, ?);");
			pst.setTimestamp(1, new java.sql.Timestamp(a.getDataHora().getTime()));
			pst.setString(2, a.getDesc());
			pst.setString(3, a.getRes());
			pst.setInt(4, a.getProduto().getId());
			pst.setInt(5, a.getTipoAtendimento().getId());
			pst.setInt(6, a.getCliente().getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<Atendimento> buscarAtendimentos() {
		PreparedStatement pst;
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_atendimento;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Atendimento a = new Atendimento();
				a.setId(rs.getInt("id_atendimento"));				
				a.setDataHora(rs.getTimestamp("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setSolucao(rs.getString("solucao_atendimento"));
				a.setProduto(ProdutoFacade.buscarProduto(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setCliente(UsuarioFacade.buscarCliente(Integer.parseInt(rs.getString("id_cliente"))));

				atendimentos.add(a);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//inverte a ordem do banco
		Collections.reverse(atendimentos);
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
				a.setDataHora(rs.getTimestamp("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setSolucao(rs.getString("solucao_atendimento"));
				a.setProduto(ProdutoFacade.buscarProduto(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setCliente(UsuarioFacade.buscarCliente(Integer.parseInt(rs.getString("id_cliente"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public static List<Atendimento> buscarAtendimentosPorClienteId(int clienteId) {
		PreparedStatement pst;
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_atendimento WHERE id_cliente = ?;");
			pst.setInt(1, clienteId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Atendimento a = new Atendimento();
				a.setId(rs.getInt("id_atendimento"));
				a.setDataHora(rs.getTimestamp("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setSolucao(rs.getString("solucao_atendimento"));
				a.setProduto(ProdutoFacade.buscarProduto(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setCliente(UsuarioFacade.buscarCliente(Integer.parseInt(rs.getString("id_cliente"))));

				atendimentos.add(a);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//inverte a ordem do banco
		Collections.reverse(atendimentos);
		return atendimentos;
	}
	public static List<Atendimento> buscarAtendimentosEmAberto() {
		PreparedStatement pst;
		List<Atendimento> atendimentos = new ArrayList<Atendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_atendimento WHERE res_atendimento = 'N';");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Atendimento a = new Atendimento();
				a.setId(rs.getInt("id_atendimento"));
				a.setDataHora(rs.getTimestamp("dt_hr_atendimento"));
				a.setDesc(rs.getString("desc_atendimento"));
				a.setRes(rs.getString("res_atendimento"));
				a.setSolucao(rs.getString("solucao_atendimento"));
				a.setProduto(ProdutoFacade.buscarProduto(Integer.parseInt(rs.getString("id_produto"))));
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(rs.getString("id_tipo_atendimento"))));
				a.setCliente(UsuarioFacade.buscarCliente(Integer.parseInt(rs.getString("id_cliente"))));

				atendimentos.add(a);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//inverte a ordem do banco
		Collections.reverse(atendimentos);
		return atendimentos;
	}

	public static void alterarAtendimento(Atendimento a) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_atendimento SET solucao_atendimento = ?, res_atendimento = ? WHERE id_atendimento = ?;");
			pst.setString(1, a.getSolucao());
			pst.setString(2, a.getRes());
			pst.setInt(3, a.getId());
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

	public static List<TipoAtendimento> buscarTiposAtendimento() {
		PreparedStatement pst;
		List<TipoAtendimento> tipoAtendimentos = new ArrayList<TipoAtendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_tipo_atendimento;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				TipoAtendimento ta = new TipoAtendimento();
				ta.setId(rs.getInt("id_tipo_atendimento"));
				ta.setNome(rs.getString("nome_tipo_atendimento"));

				tipoAtendimentos.add(ta);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoAtendimentos;
	}

	public static TipoAtendimento buscarTipoAtendimentoPorId(int id) {
		PreparedStatement pst;
		TipoAtendimento ta = new TipoAtendimento();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_tipo_atendimento WHERE id_tipo_atendimento=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				ta.setId(rs.getInt("id_tipo_atendimento"));
				ta.setNome(rs.getString("nome_tipo_atendimento"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ta;
	}
}
