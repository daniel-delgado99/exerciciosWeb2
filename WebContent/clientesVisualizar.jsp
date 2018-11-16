<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
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
							Estado
							<select id="estado" name="estado" disabled class="form-control">
								<option selected>${cliente.cidade.estado.nome}</option>
							</select>
						</div>
						<div class="col-md-6">						
							Cidade
							<select id="cidade" name="cidade"  disabled class="form-control">
								<option selected>${cliente.cidade.nome}</option>
							</select>
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