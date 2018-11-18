<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %> 
	<title>Lista de categorias</title>
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
			<div class="col-md-12">
				<div class="row">
					<div class="col-md-10">	
						<h1>Categorias</h1>
					</div>
					<div class="col-md-2">
						<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/CategoriaServlet?action=formNew">Nova</a>
					</div>
				</div>
				<c:set var="categorias" scope="request" value="${categorias}"/>
				
				<c:if test="${categorias.size() == 0}">
					<p>Nenhum atendimento encontrado</p>
				</c:if>
				<c:if test="${categorias.size() != 0}"> 
					<table class="table custom-table">	      
						<tr>
							<th>Nome da categoria</th>
							<th class="action">Acoes</th>
						</tr>
						<c:forEach items="${categorias}" var="categoria">
							<tr>
								<td><c:out value="${ categoria.nome }"/></td>
								<td>
									<a class="btn btn-warning" href='${pageContext.request.contextPath}/CategoriaServlet?action=formUpdate&id=${categoria.id}'><i class="far fa-edit"></i></a>
									<a class="btn btn-danger" href='${pageContext.request.contextPath}/CategoriaServlet?action=remove&id=${categoria.id}'><i class="fas fa-trash"></i></a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:if>
				<div>
					<a class="btn btn-primary" href='${pageContext.request.contextPath}/portal.jsp'>Voltar para portal</a>
				</div>
			</div>
		</div>
	</c:if>

</body>
</html>