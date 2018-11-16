package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.TipoUsuario;

public class TipoUsuarioDAO {
static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	/*public static void insertTipoUsuario(TipoUsuario ta) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_tipo_atendimento (nome_tipo_atendimento) values (?);");
			pst.setString(1, ta.getNome());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	public static List<TipoUsuario> buscarTiposUsuario() {
		PreparedStatement pst;
		List<TipoUsuario> tiposUsuarios = new ArrayList<TipoUsuario>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_tipo_usuario;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				TipoUsuario tu = new TipoUsuario();
				tu.setId(rs.getInt("id_tipo_usuario"));
				tu.setNome(rs.getString("nome_tipo_usuario"));
				
				tiposUsuarios.add(tu);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiposUsuarios;
	}
	
	public static TipoUsuario buscarTipoUsuarioPorId(int id) {
		PreparedStatement pst;
		TipoUsuario tu = new TipoUsuario();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_tipo_usuario WHERE id_tipo_usuario=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				tu.setId(rs.getInt("id_tipo_usuario"));
				tu.setNome(rs.getString("nome_tipo_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tu;
	}
	
	/*public static void alterarProduto(TipoUsuario ta) {
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
