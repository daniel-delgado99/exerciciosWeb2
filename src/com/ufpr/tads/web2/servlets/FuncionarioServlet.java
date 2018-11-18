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

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cidade;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Funcionario;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.beans.Estado;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.CidadeEstadoFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;

@WebServlet("/FuncionarioServlet")
public class FuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FuncionarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null || ((Funcionario) session.getAttribute("login")).getTipoUsuario().getId() != 2) {
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
			if (action == null || action == "" || action == "listarAtendimentos") {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AtendimentoServlet");
				try {
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("verAtendimento")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Atendimento a = AtendimentoFacade.buscarAtendimento(id);
				List<Produto> produtos = ProdutoFacade.buscarTodosProdutos();
				List<TipoAtendimento> tiposAtendimento = AtendimentoFacade.buscarTodosTiposAtendimento();
				List<Cliente> clientes = UsuarioFacade.buscarTodosClientes();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoDetalhes.jsp");
				try {
					request.setAttribute("produtos", produtos);
					request.setAttribute("tiposAtendimento", tiposAtendimento);
					request.setAttribute("clientes", clientes);
					request.setAttribute("atendimento", a);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("formUpdate")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente c = UsuarioFacade.buscarCliente(id);
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
