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
import com.ufpr.tads.web2.facade.ProdutoFacade;


@WebServlet("/CategoriaServlet")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CategoriaServlet() {
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
				List<CategoriaProduto> categorias = ProdutoFacade.buscarTodosCategoriasProduto();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/categoriaListar.jsp");
				try {
					request.setAttribute("categorias", categorias);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("show")) {
				int id = Integer.parseInt(request.getParameter("id"));
				CategoriaProduto c = ProdutoFacade.buscarCategoriaProduto(id);
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoriaVisualizar.jsp");
				try {
					request.setAttribute("categoria", c);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("formUpdate")) {
				int id = Integer.parseInt(request.getParameter("id"));
				CategoriaProduto c = ProdutoFacade.buscarCategoriaProduto(id);
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoriaForm.jsp");
				try {
					request.setAttribute("categoria", c);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ProdutoFacade.excluirCategoriaProduto(id);

				response.sendRedirect(request.getContextPath() + "/CategoriaServlet");

			} else if (action.equals("update")) {
				CategoriaProduto c = new CategoriaProduto();
				c.setId(Integer.parseInt(request.getParameter("id")));
				c.setNome(request.getParameter("nome"));
				
				ProdutoFacade.alterarCategoriaProduto(c);

				response.sendRedirect(request.getContextPath() + "/CategoriaServlet");

			} else if (action.equals("formNew")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/categoriaForm.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			} else if (action.equals("new")) {
				CategoriaProduto c = new CategoriaProduto();
				c.setNome(request.getParameter("nome"));

				ProdutoFacade.inserirCategoriaProduto(c);

				response.sendRedirect(request.getContextPath() + "/CategoriaServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
