<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Detalhes Atendimento</title>
</head>
<body>
	<c:if test="${ empty login }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${ not empty login }">
	<c:set var="atendimento" scope="request" value="${atendimento}"/>
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<div class="form-cliente">
				<form class="" action="AtendimentoServlet?action=updateAtendimento&id=${atendimento.id}" method="post" class="form-group">
					<h2 class="form-title">Detalhes Atendimento</h2>
					<div class="row">
						<div class="col-md-6">
							Data/Hora: <input type="text" name="dtHr" value="${atendimento.dataHora}" disabled class="form-control" required/>
						</div>
					</div>
					<c:if test="${ login.tipoUsuario != 1 }">
						<div class="row">
							<div class="col-md-6">
								Cliente
								<input type="text" name="cliente" value="${atendimento.cliente.nome}" disabled class="form-control" required/>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-6">
							Tipo de atendimento
							<input type="text" name="tipoAtendimento" value="${atendimento.tipoAtendimento.nome}" disabled class="form-control" required/>
						</div>
						<div class="col-md-6">
							Produto
							<input type="text" name="produto" value="${atendimento.produto.nome}" disabled class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							Descrição: <textarea type="text" name="desc" rows="6" disabled class="form-control" required>${atendimento.desc}</textarea>
						</div>
					</div>
					<c:if test="${not (login.tipoUsuario == 1 and atendimento.res == 'N')}"> 
						<div class="row">
							<div class="col-md-12">
								Solução:
								<textarea type="text" name="solucao" rows="6" ${atendimento.res == 'S' ? 'disabled' : '' } class="form-control" required><c:out value="${atendimento.solucao}"/></textarea>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/AtendimentoServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
						</div>
						<c:if test="${atendimento.res == 'N'}">
							<div class="col-md-6">
								<button class="btn btn-success btn-new" type="submit">Salvar</a>
							</div>
						</c:if>
					</div>
				</form>
			</div>
		</div>
	</c:if>	
</body>
</html>