package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.Produto;

public class ProdutoDAO {
	static Connection con = ConnectionFactory.getConnectionFactory().getConnection();
	
	public static void insertProduto(Produto p) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("INSERT INTO tb_produto (nome_produto) values (?);");
			pst.setString(1, p.getNome());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Produto> buscarProdutos() {
		PreparedStatement pst;
		List<Produto> produtos= new ArrayList<Produto>();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_produto;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("id_produto"));
				p.setNome(rs.getString("nome_produto"));
				
				produtos.add(p);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produtos;
	}
	
	public static Produto buscarProdutoPorId(int id) {
		PreparedStatement pst;
		Produto p = new Produto();
		try {
			pst = con.prepareStatement("SELECT * FROM tb_produto WHERE id_produto=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				p.setId(rs.getInt("id_produto"));
				p.setNome(rs.getString("nome_produto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public static void alterarProduto(Produto p) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("UPDATE tb_produto SET nome_produto = ? WHERE id_produto = ?;");
			pst.setString(1, p.getNome());
			pst.setInt(2, p.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void removerProduto(int id) {
		PreparedStatement pst;
		try {
			pst = con.prepareStatement("DELETE FROM tb_produto WHERE id_produto = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
