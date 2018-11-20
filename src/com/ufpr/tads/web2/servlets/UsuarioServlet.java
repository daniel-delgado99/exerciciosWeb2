package com.ufpr.tads.web2.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.ufpr.tads.web2.beans.LoginBean;
import com.ufpr.tads.web2.beans.Funcionario;
import com.ufpr.tads.web2.beans.Gerente;
import com.ufpr.tads.web2.beans.Usuario;
import com.ufpr.tads.web2.facade.CidadeEstadoFacade;
import com.ufpr.tads.web2.facade.UsuarioFacade;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		if (action != null && action.equals("autocadastro")) {
			List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/autocadastro.jsp");
			try {
				request.setAttribute("estados", estados);
				rd.forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else if (action != null && action.equals("new")) {
			Usuario u = new Usuario();
			u.setCpf(request.getParameter("cpf"));
			u.setNome(request.getParameter("nome"));
			u.setEmail(request.getParameter("email"));
			u.setSenha(request.getParameter("senha"));
			u.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(Integer.parseInt(request.getParameter("tipoUsuario"))));
			try {
				u.setData(format.parse(request.getParameter("data")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			u.setRua(request.getParameter("rua"));
			u.setNr(Integer.parseInt(request.getParameter("nr")));
			u.setCep(request.getParameter("cep"));
			u.setCidade(CidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade"))));

			UsuarioFacade.inserirUsuario(u);
			
			if (session.getAttribute("login") == null || ((LoginBean) session.getAttribute("login")).getId() == 1) {
				response.sendRedirect(request.getContextPath() + "/index.jsp");
			} else {
				response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
			}

		} else {		
			if (session.getAttribute("login") == null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
				String msg = "Usuario deve se autenticar ao sistema";
				try {
					request.setAttribute("msg", msg);
					dispatcher.forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			} else {
				if (action == null || action == "" || action == "list") {
					if (((LoginBean) session.getAttribute("login")).getTipoUsuario() == 3) {	
						List<Funcionario> funcionarios =  UsuarioFacade.buscarTodosFuncionarios();
						List<Gerente> gerentes =  UsuarioFacade.buscarTodosGerentes();
						List<Usuario> usuarios = new ArrayList<Usuario>();
						for (Funcionario f : funcionarios) {
							usuarios.add(f);
						}
						for (Gerente g : gerentes) {
							usuarios.add(g);
						}
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/usuarioListar.jsp");
						try {
							request.setAttribute("usuarios", usuarios);
							dispatcher.forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}
					} else {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/portal.jsp");
						try {
							dispatcher.forward(request, response);
						} catch (ServletException | IOException e) {
							e.printStackTrace();
						}
					}
				} else if (action.equals("show")) {
					int id = Integer.parseInt(request.getParameter("id"));
					Usuario u = UsuarioFacade.buscarUsuario(id);
					List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
					List<Cidade> cidades = CidadeEstadoFacade.buscarTodosCidades();
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuarioDetalhes.jsp");
					try {
						request.setAttribute("cidades", cidades);
						request.setAttribute("estados", estados);
						request.setAttribute("usuario", u);
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				} else if (action.equals("formUpdate")) {
					int id = Integer.parseInt(request.getParameter("id"));
					Usuario u = UsuarioFacade.buscarUsuario(id);
					List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
					List<Cidade> cidades = CidadeEstadoFacade.buscarCidadesPorEstado(u.getCidade().getEstado().getId());
					
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuarioForm.jsp");
					try {
						request.setAttribute("usuario", u);
						request.setAttribute("estados", estados);
						request.setAttribute("cidades", cidades);
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				} else if (action.equals("remove")) {
					int id = Integer.parseInt(request.getParameter("id"));
					UsuarioFacade.excluirUsuario(id);
	
					response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
	
				} else if (action.equals("update")) {
					Usuario u = new Usuario();
					u.setId(Integer.parseInt(request.getParameter("id")));
					u.setCpf(request.getParameter("cpf"));
					u.setNome(request.getParameter("nome"));
					u.setEmail(request.getParameter("email"));
					u.setTipoUsuario(UsuarioFacade.buscarTipoUsuario(Integer.parseInt(request.getParameter("tipoUsuario"))));
					try {
						u.setData(format.parse(request.getParameter("data")));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					u.setRua(request.getParameter("rua"));
					u.setNr(Integer.parseInt(request.getParameter("nr")));
					u.setCep(request.getParameter("cep"));
					u.setCidade(CidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade"))));
	
					UsuarioFacade.alterarUsuario(u);
	
					response.sendRedirect(request.getContextPath() + "/UsuarioServlet");
				} else if (action.equals("formNew")) {
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/usuarioForm.jsp");
					List<Estado> estados = CidadeEstadoFacade.buscarTodosEstados();
					try {
						request.setAttribute("estados", estados);
						rd.forward(request, response);
					} catch (ServletException | IOException e) {
						e.printStackTrace();
					}
				
				} 
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
