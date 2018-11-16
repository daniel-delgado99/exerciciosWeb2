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

import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.facade.CidadeEstadoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;

@WebServlet("/ClientesServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
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
				List<Cliente> clientes = UsuarioFacade.buscarTodosClientes();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientesListar.jsp");
				try {
					request.setAttribute("clientes", clientes);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("show")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente c = (Cliente) UsuarioFacade.buscarUsuario(id);
				List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
				List<Cidade> cidades = CidadeEstadoFacade.buscarTodosCidades();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
				try {
					request.setAttribute("cidades", cidades);
					request.setAttribute("estados", estados);
					request.setAttribute("cliente", c);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("formUpdate")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente c = (Cliente) UsuarioFacade.buscarUsuario(id);
				List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
				try {
					request.setAttribute("estados", estados);
					request.setAttribute("cliente", c);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("id"));
				UsuarioFacade.excluirUsuario(id);

				response.sendRedirect(request.getContextPath() + "/ClientesServlet");

			} else if (action.equals("update")) {
				Cliente c = new Cliente();
				c.setId(Integer.parseInt(request.getParameter("id")));
				c.setCpf(request.getParameter("cpf"));
				c.setNome(request.getParameter("nome"));
				c.setEmail(request.getParameter("email"));
				c.setData(request.getParameter("data"));
				c.setRua(request.getParameter("rua"));
				c.setNr(Integer.parseInt(request.getParameter("nr")));
				c.setCep(request.getParameter("cep"));
				c.setCidade(CidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade"))));

				UsuarioFacade.alterarUsuario(c);

				response.sendRedirect(request.getContextPath() + "/ClientesServlet");

			} else if (action.equals("formNew")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
				List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
				try {
					request.setAttribute("estados", estados);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}

			} else if (action.equals("new")) {
				Cliente c = new Cliente();
				c.setCpf(request.getParameter("cpf"));
				c.setNome(request.getParameter("nome"));
				c.setEmail(request.getParameter("email"));
				c.setData(request.getParameter("data"));
				c.setRua(request.getParameter("rua"));
				c.setNr(Integer.parseInt(request.getParameter("nr")));
				c.setCep(request.getParameter("cep"));
				c.setCidade(CidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade"))));

				UsuarioFacade.inserirUsuario(c);

				response.sendRedirect(request.getContextPath() + "/ClientesServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
