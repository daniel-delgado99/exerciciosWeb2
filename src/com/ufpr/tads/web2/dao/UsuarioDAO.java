package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.Usuario;

public class UsuarioDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
//	List<Usuario> getUsuarios() {
//		return null;
//	}
//	Usuario findById(int id);
	public static void insertUsuario(Usuario u) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_usuario(nome_usuario, login_usuario, senha_usuario) values (?,?,?)");
			pst.setString(1, u.getNome());
			pst.setString(2, u.getLogin());
			pst.setString(3, u.getSenha());
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
				u.setNome(rs.getString("nome_usuario"));
				u.setLogin(rs.getString("login_usuario"));
				u.setSenha(rs.getString("senha_usuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
//	void updateUsuario(Usuario u);
//	void deleteUsuario(Usuario u);
}
