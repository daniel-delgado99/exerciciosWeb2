<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.ufpr.tads.web2.beans.Cliente" %>
<%@ page errorPage="erro.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/styles.css"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Visualizar cliente</title>
</head>
<body>
	<c:if test="${loginBean.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${loginBean.nome != null }">
	<c:set var="cliente" scope="request" value="${cliente}"/>
		<div class="flex-container">
			<div class="form-cliente">
				<form class="purple-box" class="form-group">
					<h2 class="form-title">Visualizar cliente</h2>
					<div class="row">
						<div class="col-md-6">
							CPF: <input  type="text" name="cpf" maxlength="11" class="form-control"  value="${cliente.cpf}" disabled/>
						</div>
						<div class="col-md-6">
							Nome: <input type="text" name="nome" class="form-control" value="${cliente.nome}" disabled/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Email <input type="email" name="email" class="form-control"  value="${cliente.email}" disabled/>
						</div>
						<div class="col-md-6">
							Data <input type="text" name="data" class="form-control" value="${cliente.data}" disabled/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Rua <input type="text" name="rua" class="form-control" value="${cliente.rua}" disabled/>
						</div>
						<div class="col-md-2">
							Numero <input type="number" name="nr" class="form-control" value="${cliente.nr}" disabled/>
						</div>
						<div class="col-md-4">
							CEP <input type="text" name="cep" maxlength="8" class="form-control" value="${cliente.cep}" disabled/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">						
							Cidade <input type="text" name="cidade" class="form-control" value="${cliente.cidade}" disabled/>
						</div>
						<div class="col-md-6">
							UF <input type="text" name="uf" maxlength="2" class="form-control" value="${cliente.uf}" disabled/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/ClientesServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:if>
</body>
</html>