<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %> 
	<title>Inserir</title>
</head>
<body>
	<c:if test="${loginBean.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${loginBean.nome != null }">
		<form action='CadastrarUsuarioServlet' method='POST'>
	        Nome: <input type='text' name='nome' required><br>
	        Login: <input type='text' name='login' required><br>
	        Senha: <input type='password' name='senha' required><br>
	        <button type='submit'>Salvar</button><br>
	    </form>
    </c:if>
</body>
</html>