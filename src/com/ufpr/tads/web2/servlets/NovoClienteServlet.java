package com.ufpr.tads.web2.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.Cliente;
import com.ufpr.tads.web2.dao.ClienteDAO;

@WebServlet("/NovoClienteServlet")
public class NovoClienteServlet extends HttpServlet {
	ClienteDAO clienteDAO = new ClienteDAO();

    public NovoClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Cliente c = new Cliente();
			c.setCpf(request.getParameter("cpf"));
			c.setNome(request.getParameter("nome"));
			c.setEmail(request.getParameter("email"));
			c.setData(request.getParameter("data"));
			c.setRua(request.getParameter("rua"));
			c.setNr( Integer.parseInt(request.getParameter("nr")));
			c.setCep(request.getParameter("cep"));
			c.setCidade(request.getParameter("cidade"));
			c.setUf(request.getParameter("uf"));
			
			clienteDAO.insertCliente(c);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ClientesServlet");
	        try {
				dispatcher.forward(request,response);
	        } catch (ServletException | IOException e) {
     			e.printStackTrace();
     		}
		}		
	}
}
