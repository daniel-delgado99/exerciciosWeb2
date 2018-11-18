package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.CategoriaProduto;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.facade.ProdutoFacade;


@WebServlet("/ProdutoServlet")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			String msg = "Usuario deve se autenticar ao sistema";
			try {
				request.setAttribute("msg", msg);
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			String action = request.getParameter("action");
			if (action == null || action == "" || action == "list") {
				List<Produto> produtos = ProdutoFacade.buscarTodosProdutos();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/produtoListar.jsp");
				try {
					request.setAttribute("produtos", produtos);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("show")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Produto p = ProdutoFacade.buscarProduto(id);
				List<CategoriaProduto> categorias = ProdutoFacade.buscarTodosCategoriasProduto();
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/produtoDetalhes.jsp");
				try {
					request.setAttribute("categorias", categorias);
					request.setAttribute("produto", p);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("formUpdate")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Produto p = ProdutoFacade.buscarProduto(id);
				List<CategoriaProduto> categorias = ProdutoFacade.buscarTodosCategoriasProduto();
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/produtoForm.jsp");
				try {
					request.setAttribute("categorias", categorias);
					request.setAttribute("produto", p);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ProdutoFacade.excluirProduto(id);

				response.sendRedirect(request.getContextPath() + "/ProdutoServlet");

			} else if (action.equals("update")) {
				Produto p = new Produto();
				p.setId(Integer.parseInt(request.getParameter("id")));
				p.setNome(request.getParameter("nome"));
				p.setCategoriaProduto(ProdutoFacade.buscarCategoriaProduto(Integer.parseInt(request.getParameter("categoria"))));
				p.setDesc(request.getParameter("desc"));
				p.setPeso(Integer.parseInt(request.getParameter("peso")));
				
				ProdutoFacade.alterarProduto(p);

				response.sendRedirect(request.getContextPath() + "/ProdutoServlet");

			} else if (action.equals("formNew")) {
				List<CategoriaProduto> categorias = ProdutoFacade.buscarTodosCategoriasProduto();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/produtoForm.jsp");
				try {
					request.setAttribute("categorias", categorias);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			} else if (action.equals("new")) {
				Produto p = new Produto();
				p.setNome(request.getParameter("nome"));
				p.setCategoriaProduto(ProdutoFacade.buscarCategoriaProduto(Integer.parseInt(request.getParameter("categoria"))));
				p.setDesc(request.getParameter("desc"));
				p.setPeso(Integer.parseInt(request.getParameter("peso")));

				ProdutoFacade.inserirProduto(p);

				response.sendRedirect(request.getContextPath() + "/ProdutoServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
