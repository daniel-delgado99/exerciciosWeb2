<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %> 
	<title>Portal</title>
</head>
<body>
	<c:if test="${loginBean.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${loginBean.nome != null }">
	<div class="flex-container">
		<div class="purple-box login">
			<div class="col-md-12">
				<h1>Bem vindo, ${loginBean.nome}!</h1>
				<h3>Menu</h3>
				<div>
					<a class="btn btn-primary" href='${pageContext.request.contextPath}/ClientesServlet'>Cadastro de clientes</a>
					<a class="btn btn-warning" href='${pageContext.request.contextPath}/LogoutServlet'>Logout</a>
				</div>
				<div class="footer">
					<p>Em caso de problemas, contatar o adminstrador.</p>
					<p>${applicationScope['adminEmail']}</p>
				</div>
			</div>
		</div>
	</div>
    </c:if>
</body>
</html>