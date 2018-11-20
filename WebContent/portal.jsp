<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %> 
	<title>Portal</title>
</head>
<body>
	<c:if test="${ empty login }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${ not empty login }">
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<h1 class="form-title">Sobre</h1>
			<p>Miga, sua loka!</p>
			<p>A BEIBE é uma empresa de embelezamento artificial voltada ao público jovem e adulto que quer fazer a diferença no mundo.</p>
			<p>
				A Embuste é a marca de beleza preferida dos brasileiros (Fonte: Embuste Estatística, 2018).
				Todos os nossos produtos tocam sua beleza, é assim que a marca transforma momentos simples em momentos embusteiros há menos de 0 anos.
			</p>
			<p>Nosso objetivo é tornar o mundo cada vez mais belo e embusteiro, e as pessoas mais felizes.</p>
			<p class="slogan">E ai, vamos ficar lindas?</p>
		</div>
    </c:if>
</body>
</html>