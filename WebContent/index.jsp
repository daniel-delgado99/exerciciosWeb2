<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page errorPage="erro.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
   		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
		<div class="flex-container">
			<div class="purple-box login">
				<div class="form-content">
					<h4>INICIAR SESSAO</h4>
					<form class="form-group" action="LoginServlet" method="POST">
						Nome de usuario<input class="form-control purple-input" type="text" name="login"><br>
						Senha<input class="form-control purple-input" type="password" name="senha"><br>
						<% if (request.getAttribute("msg") != null) { %>
							<span style="color: red"><%= request.getAttribute("msg") %></span>
						<% } %>
						<button class="btn login-button" type="submit">Iniciar sessao</button><br>
					</form>
				</div>
				<div class="footer">
					<p>Em caso de problemas, contatar o adminstrador.</p>
					<p>${applicationScope['adminEmail']}</p>
				</div>
			</div>
		</div>     
    </body>
</html>
