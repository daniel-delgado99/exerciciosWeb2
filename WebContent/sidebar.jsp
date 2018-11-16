<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<div class="sidebar">
		<div class="head">
			<img class="logo" src="./css/icons/logo-purple.png">
		</div>
		<div class="links">
			<a class="btn" href='${pageContext.request.contextPath}/ClientesServlet'>Cadastro de clientes</a>
			<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet'>Atendimentos</a>
			<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=formNew'>Efetuar atendimento</a>
			<a class="btn" href='${pageContext.request.contextPath}/LogoutServlet'>Logout</a>
		</div>
	</div>
</html>