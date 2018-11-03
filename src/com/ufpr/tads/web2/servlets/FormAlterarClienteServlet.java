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

@WebServlet("/FormAlterarClienteServlet")
public class FormAlterarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ClienteDAO clienteDAO = new ClienteDAO();
       
    public FormAlterarClienteServlet() {
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
			int id = Integer.parseInt(request.getParameter("id"));	
			Cliente c = clienteDAO.buscarClientePorId(id);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesAlterar.jsp");
			try {
	        	request.setAttribute("cliente", c);
				rd.forward(request,response);
	        } catch (ServletException | IOException e) {
     			e.printStackTrace();
     		}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
