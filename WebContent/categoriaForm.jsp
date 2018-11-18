<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Categoria</title>
</head>
<body>
	<c:if test="${ empty login }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${ not empty login }">
		<c:set var="categoria" scope="request" value="${categoria}"/>
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<div class="form-cliente">
				<form class="" action="${not empty categoria ? 'CategoriaServlet?action=update' : 'CategoriaServlet?action=new'}" method="post" class="form-group">
					<c:if test="${not empty categoria}">
						<input type="hidden" name="id" value="${categoria.id}"/>
						<h2 class="form-title">Alterar categoria</h2>
					</c:if>
					<c:if test="${empty categoria}">
						<h2 class="form-title">Nova categoria</h2>
					</c:if>
					<div class="row">
						<div class="col-md-6">
							Nome da categoria: <input  type="text" name="nome" value="${not empty categoria ? categoria.nome : ''}" class="form-control" required/>
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/CategoriaServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
						</div>
						<div class="col-md-6">
							<button class="btn btn-success btn-new" type="submit">Enviar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:if>	
</body>
</html>