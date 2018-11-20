<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Visualizar cliente</title>
</head>
<body>
	<c:if test="${ empty login }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${ not empty login }">
	<c:set var="usuario" scope="request" value="${usuario}"/>
		<%@ include file="sidebar.jsp" %>
		<div class="content-container">
			<form class="" class="form-group">
				<h2 class="form-title">Visualizar usuários</h2>
				<div class="row">
					<div class="col-md-6">
						CPF: <input  type="text" name="cpf" maxlength="11" class="form-control"  value="${usuario.cpf}" disabled/>
					</div>
					<div class="col-md-6">
						Nome: <input type="text" name="nome" class="form-control" value="${usuario.nome}" disabled/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Email <input type="email" name="email" class="form-control"  value="${usuario.email}" disabled/>
					</div>
					
					<fmt:formatDate pattern="dd/MM/yyyy" value="${usuario.data}" var="dataFormatada"/>
					<div class="col-md-6">
						Data <input type="text" name="data" class="form-control" value="${dataFormatada}" disabled/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Tipo de usuário
						<select name="tipoUsuario" disabled class="form-control">
							<option value="" selected disabled>Selecione um tipo de usuario</option>
							<option value="2" ${not empty usuario && usuario.tipoUsuario.id == 2 ? 'selected' : ''}>Funcionario</option>
							<option value="3" ${not empty usuario && usuario.tipoUsuario.id == 3 ? 'selected' : ''}>Gerente</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Rua <input type="text" name="rua" class="form-control" value="${usuario.rua}" disabled/>
					</div>
					<div class="col-md-2">
						Numero <input type="number" name="nr" class="form-control" value="${usuario.nr}" disabled/>
					</div>
					<div class="col-md-4">
						CEP <input type="text" name="cep" maxlength="8" class="form-control" value="${usuario.cep}" disabled/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Estado
						<select id="estado" name="estado" disabled class="form-control">
							<option selected>${usuario.cidade.estado.nome}</option>
						</select>
					</div>
					<div class="col-md-6">						
						Cidade
						<select id="cidade" name="cidade"  disabled class="form-control">
							<option selected>${usuario.cidade.nome}</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href='${pageContext.request.contextPath}/UsuarioServlet' class="btn btn-warning btn-new" type="button">Voltar</a>
					</div>
				</div>
			</form>
		</div>
	</c:if>
</body>
</html>