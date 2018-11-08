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
import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.beans.Produto;
import com.ufpr.tads.web2.beans.TipoAtendimento;
import com.ufpr.tads.web2.dao.UsuarioDAO;
import com.ufpr.tads.web2.facade.AtendimentoFacade;
import com.ufpr.tads.web2.facade.ClientesFacade;

@WebServlet("/AtendimentoServlet")
public class AtendimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AtendimentoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		    String msg = "Usuario deve se autenticar ao sistema";
	        try {
	        	request.setAttribute("msg", msg);
				dispatcher.forward(request,response);
	        } catch (ServletException | IOException e) {
     			e.printStackTrace();
     		}
		} else {
			String action = request.getParameter("action");
			if (action == null || action == "" || action == "list") {
				List<Atendimento> atendimentos = AtendimentoFacade.buscarTodosAtendimentos();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/atendimentoListar.jsp");
		        try {
		        	request.setAttribute("atendimentos", atendimentos);
					dispatcher.forward(request,response);
		        } catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
			} else if (action.equals("show")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Atendimento a = AtendimentoFacade.buscarAtendimento(id);
				List<Produto> produtos = AtendimentoFacade.buscarTodosProdutos();
				List<TipoAtendimento> tiposAtendimento = AtendimentoFacade.buscarTodosTiposAtendimento();
				List<Cliente> clientes = ClientesFacade.buscarTodos();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimentoDetalhes.jsp");
				try {
					request.setAttribute("produtos", produtos);
					request.setAttribute("tiposAtendimento", tiposAtendimento);
					request.setAttribute("clientes", clientes);
		        	request.setAttribute("atendimento", a);
					rd.forward(request,response);
				} catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
			} else if (action.equals("formNew")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/atendimento.jsp");
				List<Produto> produtos = AtendimentoFacade.buscarTodosProdutos();
				List<TipoAtendimento> tiposAtendimento = AtendimentoFacade.buscarTodosTiposAtendimento();
				List<Cliente> clientes = ClientesFacade.buscarTodos();
				try {
					request.setAttribute("produtos", produtos);
					request.setAttribute("tiposAtendimento", tiposAtendimento);
					request.setAttribute("clientes", clientes);
					rd.forward(request,response);
				} catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
		
			} else if (action.equals("new")) {
				Atendimento a = new Atendimento();
				a.setDataHora(request.getParameter("dtHr"));
				a.setTipoAtendimento(AtendimentoFacade.buscarTipoAtendimento(Integer.parseInt(request.getParameter("tipoAtendimento"))));
				a.setDesc(request.getParameter("desc"));
				a.setProduto(AtendimentoFacade.buscarProduto(Integer.parseInt(request.getParameter("produto"))));
				a.setCliente(ClientesFacade.buscar(Integer.parseInt(request.getParameter("cliente"))));
				a.setRes(request.getParameter("res"));
				a.setUsuario(UsuarioDAO.buscarUsuarioPorId(Integer.parseInt(request.getParameter("usuario"))));
				
				AtendimentoFacade.inserirAtendimento(a);
				
				response.sendRedirect(request.getContextPath() + "/AtendimentoServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
