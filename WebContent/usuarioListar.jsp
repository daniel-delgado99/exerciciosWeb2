<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="./header.jsp" %>  
	<title>Lista de Clientes</title>
</head>
<body>
	<c:if test="${empty login}">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${not empty login}">
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<div class="row">
				<div class="col-md-10">
					<h1>Usuários cadastrados</h1>
				</div>
				<div class="col-md-2">
					<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/UsuarioServlet?action=formNew">Novo</a>
				</div>
			</div>
		
			<c:set var="usuarios" scope="request" value="${usuarios}"/>
			
			<c:if test="${ usuarios.size() == 0 }">
				<p>Nada a mostrar</p>
			</c:if>
			<c:if test="${ usuarios.size() > 0  }"> 
				<table class="table custom-table">	      
					<tr>
						<th>Cpf</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Ações</th>
					</tr>
					<c:forEach items="${usuarios}" var="usuario">
						<tr>
							<td><c:out value="${usuario.cpf}"/></td>
							<td><c:out value="${usuario.nome}"/></td>
							<td><c:out value="${usuario.email}"/></td>
							<td>
								<a class="btn btn-success" href='${pageContext.request.contextPath}/UsuarioServlet?action=show&id=${usuario.id}'><i class="far fa-eye"></i></a>
								<a class="btn btn-warning" href='${pageContext.request.contextPath}/UsuarioServlet?action=formUpdate&id=${usuario.id}'><i class="far fa-edit"></i></a>
								<c:if test="${ login.id != usuario.id }">
									<a class="btn btn-danger" href='${pageContext.request.contextPath}/UsuarioServlet?action=remove&id=${usuario.id}' onclick="return confirm('Deseja realmente excluir esse usuário?');"><i class="far fa-trash-alt"></i></a>
								</c:if>
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