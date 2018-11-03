package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
//	void updateUsuario(Usuario u);
//	void deleteUsuario(Usuario u);
}
