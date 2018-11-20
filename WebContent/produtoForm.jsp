<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Produto</title>
</head>
<body>
	<c:if test="${ empty login }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${ not empty login }">
		<c:set var="produto" scope="request" value="${produto}"/>
		<c:set var="categorias" scope="request" value="${categorias}"/>
		
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<div class="form-cliente">
				<form class="" action="${not empty produto ? 'ProdutoServlet?action=update' : 'ProdutoServlet?action=new'}" method="post" class="form-group">
					<c:if test="${not empty produto}">
						<input type="hidden" name="id" value="${produto.id}"/>
						<h2 class="form-title">Alterar produto</h2>
					</c:if>
					<c:if test="${empty produto}">
						<h2 class="form-title">Novo produto</h2>
					</c:if>
					<div class="row">
						<div class="col-md-6">
							Nome do produto: <input type="text" name="nome" value="${not empty produto ? produto.nome : ''}" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Categoria
							<select name="categoria" class="form-control">
								<option value="" disabled>Selecione uma categoria</option>
								<c:forEach items="${categorias}" var="categoria">
									<option value="${categoria.id}" ${not empty produto && produto.categoriaProduto.id == categoria.id ? 'selected' : ''}><c:out value="${categoria.nome}"/></option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Peso (em g): <input type="number" name="peso" value="${not empty produto ? produto.peso : ''}" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							Descrição: <textarea type="text" name="desc" rows="6" class="form-control" required>${not empty produto ? produto.desc : ''}</textarea>
						</div>
					</div>				
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/ProdutoServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
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