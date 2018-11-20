package com.ufpr.tads.web2.servlets;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.facade.LoginFacade;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        LoginBean loginBean = LoginFacade.efetuarLogin(email, senha);
        if (loginBean != null) {
        	this.setSession(request, loginBean);    
        	String page = "";
        	
        	switch (loginBean.getTipoUsuario()) {
        		case 1:
        			page = "/AtendimentoServlet";
        			break;
        		case 2:
        			page = "/AtendimentoServlet?action=listAbertos";
        			break;
        		case 3:
        			page = "/RelatoriosServlet";
        			break;
        		default:
        			page = "/index.jsp";
        			break;
        	}
        	try {
    			response.sendRedirect(request.getContextPath() + page);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        } else {
        	this.redirectToError(request, response);
        }
    }

    public void redirectToError(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        String msg = "Usuario ou senha invalida";
        String page = "index.jsp";
        try {
        	request.setAttribute("msg", msg);
        	request.setAttribute("page", page);
			dispatcher.forward(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }
    
    public void setSession(HttpServletRequest request, LoginBean loginUsuario) {
    	HttpSession session = request.getSession();
    	session.setAttribute("login", loginUsuario);
    }
}
