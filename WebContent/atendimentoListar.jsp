<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="./header.jsp" %>  
	<title>Lista de Atendimentos</title>
</head>
<body>
	<c:if test="${login.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${login.nome != null }">
		<div class="">
			<%@ include file="sidebar.jsp" %>
			<div class="content-container">
				<div class="col-md-12">
					<div class="row">
						<div class="col-md-10">
							<h1>Atendimentos realizados</h1>
						</div>
						<div class="col-md-2">
							<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/AtendimentoServlet?action=formNew">Novo</a>
						</div>
					</div>
					<c:set var="atendimentos" scope="request" value="${atendimentos}"/>
					
					<c:if test="${atendimentos == null}">
						<p>Nada a mostrar</p>
					</c:if>
					<c:if test="${atendimentos != null}"> 
						<table class="table custom-table">	      
							<tr>
								<th>Data/Hora</th>
								<th>Produto</th>
								<th>Nome do cliente</th>
								<th class="action">Acoes</th>
							</tr>
							<c:forEach items="${atendimentos}" var="atendimento">
								<tr>
									<td><c:out value="${atendimento.dataHora}"/></td>
									<td><c:out value="${atendimento.produto.nome}"/></td>
									<td><c:out value="${atendimento.cliente.nome}"/></td>
									<td>
										<a class="btn purple-btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=show&id=${atendimento.id}'>Ver atendimento</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div>
							<a class="btn btn-primary" href='${pageContext.request.contextPath}/portal.jsp'>Voltar para portal</a>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>