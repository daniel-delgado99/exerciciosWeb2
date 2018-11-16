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

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        
        LoginBean loginBean = LoginFacade.efetuarLogin(email, senha);
        if (loginBean != null) {
        	this.setSession(request, loginBean);        	
        	this.redirectToPortal(request, response);
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
    
    public void redirectToPortal(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/portal.jsp");
        try {
			dispatcher.forward(request,response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }
    
    public void setSession(HttpServletRequest request, LoginBean loginUsuario) {
    	HttpSession session = request.getSession();
    	session.setAttribute("login", loginUsuario);
		// session.setAttribute("username", loginUsuario.getNome());
		// session.setAttribute("userid", loginUsuario.getId());
		// session.setAttribute("someKey","someValue");
    }
}
