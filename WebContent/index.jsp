<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="erro.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
   		<%@ include file="header.jsp" %>
		<title>Login</title>
    </head>
    <body>
	    <c:if test="${login != null }">	
			<jsp:forward page="portal.jsp"> 
				<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
			</jsp:forward> 
		</c:if>
		<c:if test="${login == null }">
			<div class="flex-container">
				<div class="purple-box login">
					<div class="form-content">
						<h4>INICIAR SESSAO</h4>
						<form class="form-group" action="LoginServlet" method="POST">
							Nome de usuario<input class="form-control purple-input" type="email" name="email"><br>
							Senha<input class="form-control purple-input" type="password" name="senha"><br>
							<c:if test="${msg != null }">	
								<span style="color: red"><c:out value="${msg}"/></span>
							</c:if>	
							<button class="btn login-button" type="submit">Iniciar sessao</button><br>
						</form>
					</div>
					<div class="footer">
						<p>Não possui uma conta?</p>
						<a href='${pageContext.request.contextPath}/UsuarioServlet?action=autocadastro'>Cadastrar-se</a>
					</div>
				</div>
			</div>
		</c:if>     
    </body>
</html>
