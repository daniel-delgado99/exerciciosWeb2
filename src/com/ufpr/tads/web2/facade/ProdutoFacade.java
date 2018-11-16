package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import com.ufpr.tads.web2.beans.CategoriaProduto;
import com.ufpr.tads.web2.dao.CategoriaProdutoDAO;
import java.util.List;

public class ProdutoFacade {

    // produto
    public static void inserirProduto(Produto c) {
        ProdutoDAO.insertProduto(c);
    }

    public static void alterarProduto(Produto c) {
        ProdutoDAO.alterarProduto(c);
    }

    public static Produto buscarProduto(int id) {
        return ProdutoDAO.buscarProdutoPorId(id);
    }

    public static List<Produto> buscarTodosProdutos() {
        return ProdutoDAO.buscarProdutos();
    }

    public static void excluirProduto(int id) {
        ProdutoDAO.removerProduto(id);
    }


    // categoria 
    public static void inserirCategoriaProduto(CategoriaProduto c) {
        CategoriaProdutoDAO.insertCategoriaProduto(c);
    }

    public static void alterarCategoriaProduto(CategoriaProduto c) {
        CategoriaProdutoDAO.alterarCategoriaProduto(c);
    }

    public static CategoriaProduto buscarCategoriaProduto(int id) {
        return CategoriaProdutoDAO.buscarCategoriaProdutoPorId(id);
    }

    public static List<CategoriaProduto> buscarTodosCategoriasProduto() {
        return CategoriaProdutoDAO.buscarCategoriasProduto();
    }

    public static void excluirCategoriaProduto (int id) {
        CategoriaProdutoDAO.removerCategoriaProduto(id);
    }
}
