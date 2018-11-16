package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ufpr.tads.web2.beans.LoginBean;

public class LoginDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static LoginBean efetuarLogin(String email, String senha) {
		PreparedStatement pst;
		LoginBean loginBean = new LoginBean();
		try {
			pst = con.prepareStatement("SELECT id_usuario, nome_usuario, email_usuario, senha_usuario, id_tipo_usuario FROM tb_usuario where email_usuario=?");
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();
        	rs.last();
        
        	int id = rs.getInt("id_usuario");
        	String nome = rs.getString("nome_usuario");
	        String senhaBD = rs.getString("senha_usuario");
	        int tipoUsuario = rs.getInt("id_tipo_usuario");
	        
	        if (senha.equals(senhaBD)) {
	        	// aqui em cima checar criptografia
	        	loginBean.setId(id);
	        	loginBean.setNome(nome);
	        	loginBean.setEmail(email);
	        	loginBean.setTipoUsuario(tipoUsuario);
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }   
		return loginBean;
	}
}
