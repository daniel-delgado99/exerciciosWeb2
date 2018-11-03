package com.ufpr.tads.web2.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.dao.ConnectionFactory;

@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String senhaBD = "";
        int id = 0;
        int tamanho = 0;
        
        try {
        	Connection con = ConnectionFactory.getConnectionFactory().getConnection();
        	PreparedStatement pst;
			pst = con.prepareStatement("SELECT id_usuario, login_usuario, senha_usuario FROM tb_usuario where login_usuario=?");
			pst.setString(1, login);
	        ResultSet rs = pst.executeQuery();
	        
	        rs.last();
	        tamanho = rs.getRow();
	        
	        if (tamanho != 1) {
	        	this.redirectToError(request, response);
	        } else {
	        	id = rs.getInt("id_usuario");
	        	login = rs.getString("login_usuario");
		        senhaBD = rs.getString("senha_usuario");
		        
		        if (senha.equals(senhaBD)) {
		        	LoginBean loginBean = new LoginBean(id, login);
		        	this.setSession(request, loginBean);
		        	this.redirectToPortal(request, response);
		        } else {
		        	this.redirectToError(request, response);
		        }
	        }
	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public void redirectToError(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        String msg = "Usuario e senha invalidos";
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
    	session.setAttribute("loginBean", loginUsuario);
		session.setAttribute("username", loginUsuario.getNome());
		session.setAttribute("userid", loginUsuario.getId());
		//session.setAttribute("someKey","someValue");
    }
}
