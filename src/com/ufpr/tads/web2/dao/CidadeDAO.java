package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Cidade;

public class CidadeDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();

	public static List<Cidade> buscarCidades() {
		PreparedStatement pst;
		List<Cidade> cidades = new ArrayList<Cidade>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_cidade;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cidade c = new Cidade();
				c.setId(rs.getInt("id_cidade"));
				c.setNome(rs.getString("nome_cidade"));
				c.setEstado(EstadoDAO.buscarEstadoPorId(rs.getInt("id_estado")));
				
				cidades.add(c);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cidades;
	}
	
	public static Cidade buscarCidadePorId(int id) {
		PreparedStatement pst;
		Cidade c = new Cidade();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_cidade WHERE id_cidade=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				c.setId(rs.getInt("id_cidade"));
				c.setNome(rs.getString("nome_cidade"));
				c.setEstado(EstadoDAO.buscarEstadoPorId(rs.getInt("id_estado")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static List<Cidade> buscarCidadesPorEstado(int estadoId) {
		PreparedStatement pst;
		List<Cidade> cidades = new ArrayList<Cidade>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_cidade WHERE id_estado=?;");
			pst.setInt(1, estadoId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cidade c = new Cidade();
				c.setId(rs.getInt("id_cidade"));
				c.setNome(rs.getString("nome_cidade"));
				c.setEstado(EstadoDAO.buscarEstadoPorId(rs.getInt("id_estado")));
				
				cidades.add(c);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cidades;
	}
	
}
