<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<div class="sidebar">
		<div class="head">
			<a href="${pageContext.request.contextPath}/portal.jsp">
				<img class="logo" src="./css/icons/logo-purple.png" >
			</a>
		</div>
		<div class="links">
			<c:if test="${login.tipoUsuario == 1 }">
				<a class="btn" href='${pageContext.request.contextPath}/UsuarioServlet?action=formUpdate&id=${login.id}'>Alterar dados</a>
				<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet'>Meus atendimentos</a>
				<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=formNew'>Solicitar atendimento</a>
			</c:if>
			<c:if test="${login.tipoUsuario == 2 }">
				<a id="collapseButton" class="btn">Atendimentos</a>
				<div id="collapsedItems" class="collapsed-items">
					<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet'>Todos</a>
					<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=listAbertos'>Em aberto</a>				
				</div>
				<a class="btn" href='${pageContext.request.contextPath}/CategoriaServlet'>Cadastro de categorias</a>
				<a class="btn" href='${pageContext.request.contextPath}/ProdutoServlet'>Cadastro de produtos</a>
			</c:if>
			<c:if test="${login.tipoUsuario == 3 }">
				<a class="btn" href='${pageContext.request.contextPath}/UsuarioServlet'>Cadastrar funcionário</a>
				<a id="collapseButton" class="btn">Atendimentos</a>
				<div id="collapsedItems" class="collapsed-items">
					<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet'>Todos</a>
					<a class="btn" href='${pageContext.request.contextPath}/AtendimentoServlet?action=listAbertos'>Em aberto</a>				
				</div>
				<a class="btn" href='${pageContext.request.contextPath}/CategoriaServlet'>Cadastro de categorias</a>
				<a class="btn" href='${pageContext.request.contextPath}/ProdutoServlet'>Cadastro de produtos</a>
				<a class="btn" href='#'>Relatórios</a>
			</c:if>
			<a class="btn" href='${pageContext.request.contextPath}/LogoutServlet'>Logout</a>
		</div>
	</div>
</html>