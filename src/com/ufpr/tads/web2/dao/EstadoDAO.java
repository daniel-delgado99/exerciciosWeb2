package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Estado;

public class EstadoDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static List<Estado> buscarEstados() {
		PreparedStatement pst;
		List<Estado> estados = new ArrayList<Estado>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_estado;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Estado e = new Estado();
				e.setId(rs.getInt("id_estado"));
				e.setNome(rs.getString("nome_estado"));
				e.setSigla(rs.getString("sigla_estado"));
				
				estados.add(e);
			}
			rs.close();
			pst.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return estados;
	}
	
	public static Estado buscarEstadoPorId(int id) {
		PreparedStatement pst;
		Estado e = new Estado();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_estado WHERE id_estado=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				e.setId(rs.getInt("id_estado"));
				e.setNome(rs.getString("nome_estado"));
				e.setSigla(rs.getString("sigla_estado"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}
}
