package com.ufpr.tads.web2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ufpr.tads.web2.beans.CategoriaProduto;

public class CategoriaProdutoDAO {
    static Connection con = ConnectionFactory.getConnectionFactory().getConnection();

    public static void insertCategoriaProduto(CategoriaProduto c) {
        PreparedStatement pst;
        try {
            pst = con.prepareStatement("INSERT INTO tb_categoria_produto (nome_categoria_produto) values (?);");
            pst.setString(1, c.getNome());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<CategoriaProduto> buscarCategoriasProduto() {
        PreparedStatement pst;
        List<CategoriaProduto> categorias = new ArrayList<CategoriaProduto>();
        try {
            pst = con.prepareStatement("SELECT * FROM tb_categoria_produto;");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                CategoriaProduto c = new CategoriaProduto();
                c.setId(rs.getInt("id_categoria_produto"));
                c.setNome(rs.getString("nome_categoria_produto"));

                categorias.add(c);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public static CategoriaProduto buscarCategoriaProdutoPorId(int id) {
        PreparedStatement pst;
        CategoriaProduto c = new CategoriaProduto();
        try {
            pst = con.prepareStatement("SELECT * FROM tb_categoria_produto WHERE id_categoria_produto=?;");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                c.setId(rs.getInt("id_categoria_produto"));
                c.setNome(rs.getString("nome_categoria_produto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static void alterarCategoriaProduto(CategoriaProduto c) {
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(
                    "UPDATE tb_categoria_produto SET nome_categoria_produto = ? WHERE id_categoria_produto = ?;");
            pst.setString(1, c.getNome());
            pst.setInt(2, c.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removerCategoriaProduto(int id) {
        PreparedStatement pst;
        try {
            pst = con.prepareStatement("DELETE FROM tb_categoria_produto WHERE id_categoria_produto = ?;");
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
