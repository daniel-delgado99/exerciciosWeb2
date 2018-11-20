<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Relatórios</title>
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
			<h1 class="form-title">Relatórios</h1>
			<div class="footer">
				<a href="${pageContext.request.contextPath}/RelatoriosServlet?nomeRelatorio=funcionarios">
					<h5 class="fw-400"><i class="far fa-file-pdf"></i> Relatório de Funcionários</h5>
				</a>
				<a href="${pageContext.request.contextPath}/RelatoriosServlet?nomeRelatorio=produtosReclamados">
					<h5 class="fw-400"><i class="far fa-file-pdf"></i> Relatório de produtos mais reclamados</h5>
				</a>
				<a href="${pageContext.request.contextPath}/RelatoriosServlet?nomeRelatorio=atendimentosEmAberto">
					<h5 class="fw-400"><i class="far fa-file-pdf"></i> Relatório de atendimentos abertos por data</h5>
				</a>
				<a href="${pageContext.request.contextPath}/RelatoriosServlet?nomeRelatorio=reclamacoes">
					<h5 class="fw-400"><i class="far fa-file-pdf"></i> Relatório de reclamações</h5>
				</a>
			</div>
		</div>
	</c:if>	
</body>
</html>