package com.ufpr.tads.web2.servlets;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        	HttpSession session = request.getSession(false);
        	if (session != null) {
        		session.invalidate();
        	}
        	
        	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
             String msg = "Usuario desconectado com sucesso";

             try {
             	request.setAttribute("msg", msg);
     			dispatcher.forward(request,response);
     		} catch (ServletException | IOException e) {
     			e.printStackTrace();
     		}
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Deslogado</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Você foi deslogado!</h1>");
//            out.println("<a href='" + request.getContextPath() + "/'>Ir para inicio!</h1>");
//            out.println("</body>");
//            out.println("</html>");
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short deion";
    }// </editor-fold>

}
