package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.Atendimento;
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ProdutoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;

@WebServlet("/AtendimentoServlet")
public class AtendimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public AtendimentoServlet() {
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
				List<Atendimento> atendimentos;
				if (((LoginBean) session.getAttribute("login")).getTipoUsuario() != 1) {
					atendimentos = AtendimentoFacade.buscarTodosAtendimentos();
					request.setAttribute("title", "Atendimentos");
				} else {
					atendimentos = AtendimentoFacade.buscarAtendimentosPorCliente(((LoginBean) session.getAttribute("login")).getId());
					request.setAttribute("title", "Meus atendimentos");
				}
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
				try {
					request.setAttribute("atendimentos", atendimentos);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("listAbertos")) {
				List<Atendimento> atendimentos = AtendimentoFacade.buscarAtendimentosEmAberto();
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
				try {
					request.setAttribute("title", "Atendimentos em aberto");
					request.setAttribute("atendimentos", atendimentos);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else if (action.equals("show")) {
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
			} else if (action.equals("formNew")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimento.jsp");
				List<Produto> produtos = ProdutoFacade.buscarTodosProdutos();
				List<TipoAtendimento> tiposAtendimento = AtendimentoFacade.buscarTodosTiposAtendimento();
				List<Cliente> clientes = UsuarioFacade.buscarTodosClientes();
				try {
					request.setAttribute("produtos", produtos);
					request.setAttribute("tiposAtendimento", tiposAtendimento);
					request.setAttribute("clientes", clientes);
					rd.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
				

			} else if (action.equals("new")) {
				Atendimento a = new Atendimento();
				try {
					a.setDataHora(format.parse(request.getParameter("dtHr")));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(request.getParameter("tipoAtendimento"))));
				a.setDesc(request.getParameter("desc"));
				a.setProduto(ProdutoFacade.buscarProduto(Integer.parseInt(request.getParameter("produto"))));
				a.setCliente(UsuarioFacade.buscarCliente(Integer.parseInt(request.getParameter("cliente"))));
				a.setRes("N");

				AtendimentoFacade.inserirAtendimento(a);

				response.sendRedirect(request.getContextPath() + "/AtendimentoServlet");
				
			} else if (action.equals("updateAtendimento")) {
				Atendimento a = new Atendimento();
				a.setId(Integer.parseInt(request.getParameter("id")));
				a.setSolucao(request.getParameter("solucao"));
				a.setRes("S");
				AtendimentoFacade.alterarAtendimento(a);

				response.sendRedirect(request.getContextPath() + "/AtendimentoServlet");
			} else if (action.equals("delete")) {				
				AtendimentoFacade.removerAtendimento(Integer.parseInt(request.getParameter("id")));

				response.sendRedirect(request.getContextPath() + "/AtendimentoServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
