<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="./header.jsp" %>  
	<title>Lista de produtos</title>
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
						<h1>Produtos</h1>
					</div>
					<div class="col-md-2">
						<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/ProdutoServlet?action=formNew">Novo</a>
					</div>
				</div>
				<c:set var="produtos" scope="request" value="${produtos}"/>
				<c:set var="categorias" scope="request" value="${categorias}"/>
				
				
				<c:if test="${produtos.size() == 0}">
					<p>Nenhum produto encontrado</p>
				</c:if>
				<c:if test="${produtos.size() != 0}"> 
					<table class="table custom-table">	      
						<tr>
							<th>Nome</th>
							<th>Categoria</th>
							<th>Descrição</th>
							<th>Peso (g)</th>
							<th class="action">Ações</th>
						</tr>
						<c:forEach items="${produtos}" var="produto">
							<tr>
								<td><c:out value="${produto.nome}"/></td>
								<td><c:out value="${produto.categoriaProduto.nome}"/></td>
								<td><c:out value="${produto.desc}"/></td>
								<td><c:out value="${produto.peso}"/></td>
								<td>
									<a class="btn purple-btn" href='${pageContext.request.contextPath}/ProdutoServlet?action=show&id=${produto.id}'><i class="far fa-eye"></i></a>
									<a class="btn btn-warning" href='${pageContext.request.contextPath}/ProdutoServlet?action=formUpdate&id=${produto.id}'><i class="far fa-edit"></i></a>
									<a class="btn btn-danger" href='${pageContext.request.contextPath}/ProdutoServlet?action=remove&id=${produto.id}'
									onclick="return confirm('Deseja realmente excluir esse produto? Isso excluirá todos os atendimentos associados a ele');"><i class="fas fa-trash"></i></a>
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