package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.TipoAtendimento;

public class TipoAtendimentoDAO {
static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	/*public static void insertTipoAtendimento(TipoAtendimento ta) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_tipo_atendimento (nome_tipo_atendimento) values (?);");
			pst.setString(1, ta.getNome());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public static List<TipoAtendimento> buscarTiposAtendimento() {
		PreparedStatement pst;
		List<TipoAtendimento> tiposAtendimento = new ArrayList<TipoAtendimento>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_tipo_atendimento;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				TipoAtendimento ta = new TipoAtendimento();
				ta.setId(rs.getInt("id_tipo_atendimento"));
				ta.setNome(rs.getString("nome_tipo_atendimento"));
				
				tiposAtendimento.add(ta);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiposAtendimento;
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
	
	/*public static void alterarProduto(TipoAtendimento ta) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_tipo_atendimento SET nome_tipo_atendimento = ? WHERE id_tipo_atendimento = ?;");
			pst.setString(1, p.getNome());
			pst.setInt(2, p.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	/*public static void removerProduto(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/

}
