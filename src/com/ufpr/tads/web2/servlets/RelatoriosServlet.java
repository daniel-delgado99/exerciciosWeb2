package com.ufpr.tads.web2.servlets;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import com.ufpr.tads.web2.beans.LoginBean;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;


@WebServlet("/RelatoriosServlet")
public class RelatoriosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        LoginBean login = (LoginBean) session.getAttribute("login");
        
        if (login == null) {
            request.setAttribute("msg", "Usuário deve se autenticar ao sistema");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        } else {
        	String relatorio  = request.getParameter("nomeRelatorio");
	        String nomeRelatorio = "";
	        HashMap params = new HashMap();
	        
	        if (relatorio == null || relatorio == "") {
	        	 RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorio.jsp");
	             rd.forward(request, response);
	        } else {	        
		        switch(relatorio){
		        	case "funcionarios":
		        		nomeRelatorio = "/reports/funcionarios.jasper"; 
		        		break;		            
		            case "produtosReclamados":                
		                nomeRelatorio = "/reports/produtosReclamados.jasper";
		                break;    
		            case "atendimentosEmAberto":                  
		                nomeRelatorio = "/reports/atendimentosEmAberto.jasper";
		                break;
		            case "reclamacoes":
	                    nomeRelatorio = "/reports/reclamacoes.jasper";
		                break;
		            default:
	                    request.setAttribute("msg"," Opção inválida");
	                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorio.jsp");
	                    rd.forward(request, response);
	                break;            
		        }   
		        
		        try (Connection con = ConnectionFactory.getConnectionFactory().getConnection()) {                
		            String jasper = request.getContextPath() + nomeRelatorio;
		            String host = "http://" + request.getServerName() + ":" + request.getServerPort();
		            URL jasperURL = new URL(host + jasper);        
		
		            byte[] bytes = JasperRunManager.runReportToPdf(jasperURL.openStream(), params, con);
		
		            if (bytes != null) {           
		                response.setContentType("application/pdf");
		                ServletOutputStream ops = response.getOutputStream();
		                ops.write(bytes);
		                ops.flush();
		                ops.close();
		            }
		        } catch(SQLException | JRException e ) {
		            e.printStackTrace();  
		        } 
	        }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
