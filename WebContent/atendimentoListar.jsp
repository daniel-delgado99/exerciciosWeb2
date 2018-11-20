<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="./header.jsp" %>  
	<title>Lista de Atendimentos</title>
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
						<h1><c:out value="${title}"/></h1>
					</div>
					<c:if test="${login.tipoUsuario == 1}">
						<div class="col-md-2">
							<a class="btn btn-success btn-new" href="${pageContext.request.contextPath}/AtendimentoServlet?action=formNew">Novo</a>
						</div>
					</c:if>
				</div>
				<c:set var="atendimentos" scope="request" value="${atendimentos}"/>
				
				<c:if test="${atendimentos.size() == 0}">
					<p>Nenhum atendimento encontrado</p>
				</c:if>
				<c:if test="${atendimentos.size() != 0}"> 
					<table class="table custom-table">	      
						<tr>
							<th>Data/Hora</th>
							<th>Descrição</th>
							<th>Produto</th>
							<c:if test="${login.tipoUsuario != 1}">
								<th>Nome do cliente</th>
							</c:if>
							<th>Resolvido</th>
							<th class="action">Ações</th>
						</tr>
						<c:forEach items="${atendimentos}" var="atendimento">
							<tr>
								<td><fmt:formatDate pattern="dd/MM/yyyy H:mm" value="${atendimento.dataHora}"/></td>
								<td><c:out value="${atendimento.desc}"/></td>
								<td><c:out value="${atendimento.produto.nome}"/></td>
								<c:if test="${login.tipoUsuario != 1}">
									<td><c:out value="${atendimento.cliente.nome}"/></td>
								</c:if>
								<td>
									<c:if test="${atendimento.res == 'S'}">
										<span class="green">
											<i class="fas fa-check-circle"></i>
										</span>
									</c:if>
									<c:if test="${atendimento.res == 'N'}">
										<span class="red">
											<i class="far fa-times-circle"></i>
										</span>
									</c:if>
								</td>
								<td>
									<a class="btn purple-btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=show&id=${atendimento.id}'>
										<c:if test="${atendimento.res == 'S'}">
											Ver atendimento
										</c:if>
										<c:if test="${atendimento.res == 'N'}">
											Resolver atendimento
										</c:if>
									</a>
									<c:if test="${atendimento.res == 'N' and login.tipoUsuario == 1}">
										<a class="btn btn-danger btn-delete" href='${pageContext.request.contextPath}/AtendimentoServlet?action=delete&id=${atendimento.id}' 
										onclick="return confirm('Deseja realmente excluir esse atendimento?');">Deletar</a>
									</c:if>
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