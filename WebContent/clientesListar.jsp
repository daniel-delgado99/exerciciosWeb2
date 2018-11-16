<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="./header.jsp" %>  
	<title>Lista de Clientes</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<h1>Clientes Cadastrados</h1>
			</div>
			<div class="col-md-2">
				<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/ClientesServlet?action=formNew">Novo</a>
			</div>
		</div>
		<c:if test="${loginBean.nome == null }">	
			<jsp:forward page="index.jsp"> 
				<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
			</jsp:forward> 
		</c:if>
		<c:if test="${loginBean.nome != null }">
			<c:set var="clientes" scope="request" value="${clientes}"/>
			
			<c:if test="${clientes == null}">
				<p>Nada a mostrar</p>
			</c:if>
			<c:if test="${clientes != null}"> 
				<table class="table purple-table">	      
					<tr>
						<th>Cpf</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Acoes</th>
					</tr>
					<c:forEach items="${clientes}" var="cliente">
						<tr>
							<td>${cliente.cpf}</td>
							<td>${cliente.nome}</td>
							<td>${cliente.email}</td>
							<td>
								<a class="btn btn-success" href='${pageContext.request.contextPath}/ClientesServlet?action=show&id=${cliente.id}'><i class="far fa-eye"></i></a>
								<a class="btn btn-warning" href='${pageContext.request.contextPath}/ClientesServlet?action=formUpdate&id=${cliente.id}'><i class="far fa-edit"></i></a>
								<a class="btn btn-danger" href='${pageContext.request.contextPath}/ClientesServlet?action=remove&id=${cliente.id}' onclick="return confirm('Deseja realmente excluir esse usu�rio?');"><i class="far fa-trash-alt"></i></a>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div>
					<a class="btn btn-primary" href='${pageContext.request.contextPath}/portal.jsp'>Voltar para portal</a>
				</div>
			</c:if>
		</div>
	</c:if>

</body>
</html>