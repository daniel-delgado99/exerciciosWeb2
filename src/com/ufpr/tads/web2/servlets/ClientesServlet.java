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
import com.ufpr.tads.web2.dao.CidadeDAO;
import com.ufpr.tads.web2.dao.EstadoDAO;
import com.ufpr.tads.web2.facade.ClientesFacade;

@WebServlet("/ClientesServlet")
public class ClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ClientesServlet() {
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
				List<Cliente> clientes = ClientesFacade.buscarTodos();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/clientesListar.jsp");
		        try {
		        	request.setAttribute("clientes", clientes);
					dispatcher.forward(request,response);
		        } catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
			} else if (action.equals("show")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente c = ClientesFacade.buscar(id);
				List<Estado> estados = EstadoDAO.buscarEstados();
				List<Cidade> cidades = CidadeDAO.buscarCidades();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
				try {
					request.setAttribute("cidades", cidades);
					request.setAttribute("estados", estados);
		        	request.setAttribute("cliente", c);
					rd.forward(request,response);
				} catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
			} else if (action.equals("formUpdate")) {
				int id = Integer.parseInt(request.getParameter("id"));
				Cliente c = ClientesFacade.buscar(id);
				List<Estado> estados = EstadoDAO.buscarEstados();
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
				try {
					request.setAttribute("estados", estados);
		        	request.setAttribute("cliente", c);
					rd.forward(request,response);
				} catch (ServletException | IOException e) {
	     			e.printStackTrace();
	     		}
			} else if (action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ClientesFacade.excluir(id);
				
				response.sendRedirect(request.getContextPath() + "/ClientesServlet");
				
			} else if (action.equals("update")) {
				Cliente c = new Cliente();
				c.setId(Integer.parseInt(request.getParameter("id")));
				c.setCpf(request.getParameter("cpf"));
				c.setNome(request.getParameter("nome"));
				c.setEmail(request.getParameter("email"));
				c.setData(request.getParameter("data"));
				c.setRua(request.getParameter("rua"));
				c.setNr( Integer.parseInt(request.getParameter("nr")));
				c.setCep(request.getParameter("cep"));
				c.setCidade(CidadeDAO.buscarCidadePorId(Integer.parseInt(request.getParameter("cidade"))));
				
				ClientesFacade.alterar(c);
				
		        response.sendRedirect(request.getContextPath() + "/ClientesServlet");
		       
			} else if (action.equals("formNew")) {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesForm.jsp");
				List<Estado> estados = EstadoDAO.buscarEstados();
				try {
					request.setAttribute("estados", estados);
					rd.forward(request,response);
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
				c.setNr( Integer.parseInt(request.getParameter("nr")));
				c.setCep(request.getParameter("cep"));
				c.setCidade(CidadeDAO.buscarCidadePorId(Integer.parseInt(request.getParameter("cidade"))));
				
				ClientesFacade.inserir(c);
				
				response.sendRedirect(request.getContextPath() + "/ClientesServlet");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
