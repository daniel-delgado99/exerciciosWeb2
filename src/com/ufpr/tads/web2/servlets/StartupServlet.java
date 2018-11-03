package com.ufpr.tads.web2.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ufpr.tads.web2.beans.ConfigBean;

@WebListener
public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StartupServlet() {
        super();
    }
    
    public void init() {
    	System.out.println("Servlet startup iniciada");
    	ConfigBean configuracao = new ConfigBean();
    	configuracao.setAdminEmail("guslara@gmail.com");
    	ServletContext ctx = getServletContext();
    	ctx.setAttribute("adminEmail", configuracao.getAdminEmail());
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
