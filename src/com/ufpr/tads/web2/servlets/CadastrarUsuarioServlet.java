package com.ufpr.tads.web2.servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ufpr.tads.web2.beans.Usuario;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.dao.UsuarioDAO;

@WebServlet(urlPatterns = {"/CadastrarUsuarioServlet"})
public class CadastrarUsuarioServlet extends HttpServlet {
	UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarUsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastrarUsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        Usuario u = new Usuario(nome, login, senha);
        
        UsuarioDAO.insertUsuario(u);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastrarUsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 style='color: green'>Usuario cadastrado com sucesso</h1>");
            out.println("<a href='" + request.getContextPath() +"/portal.jsp'>Portal</a>");
            out.println("</body>");
            out.println("</html>");
        }        
    }

    @Override
    public String getServletInfo() {
        return "Short deion";
    }// </editor-fold>

}
