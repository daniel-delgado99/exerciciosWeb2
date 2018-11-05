<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Novo cliente</title>
</head>
<body>
	<c:if test="${loginBean.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${loginBean.nome != null }">
		<div class="flex-container">
			<div class="form-cliente">
				<form class="purple-box" action="${not empty cliente ? 'ClientesServlet?action=update' : 'ClientesServlet?action=new'}" method="post" class="form-group">
					<c:if test="${not empty cliente}">
						<input type="hidden" name="id" value="${cliente.id}"/>
						<h2 class="form-title">Alterar cliente</h2>
					</c:if>
					<c:if test="${empty cliente}">
						<h2 class="form-title">Novo cliente</h2>
					</c:if>
					<div class="row">
						<div class="col-md-6">
							CPF: <input  type="text" name="cpf" maxlength="11" value="${not empty cliente ? cliente.cpf : ''}" class="form-control" required/>
						</div>
						<div class="col-md-6">
							Nome: <input type="text" name="nome" value="${not empty cliente ? cliente.nome : ''}" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Email <input type="email" name="email" value="${not empty cliente ? cliente.email : ''}" class="form-control" required/>
						</div>
						<div class="col-md-6">
							Data <input type="date" name="data" value="${not empty cliente ? cliente.data : ''}" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Rua <input type="text" name="rua" value="${not empty cliente ? cliente.rua : ''}" class="form-control" required/>
						</div>
						<div class="col-md-2">
							Numero <input type="number" name="nr" value="${not empty cliente ? cliente.nr : ''}" class="form-control" required/>
						</div>
						<div class="col-md-4">
							CEP <input type="text" name="cep" maxlength="8"  value="${not empty cliente ? cliente.cep : ''}" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Estado
							<select id="estado" name="estado" class="form-control">
								<option value="" selected disabled>Selecione um estado</option>
								<c:forEach items="${estados}" var="estado">
									<option value="${estado.id}" ${not empty cliente && cliente.cidade.estado.id == estado.id ? 'selected' : ''}>${estado.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">						
							Cidade
							<select id="cidade" name="cidade" class="form-control">
								<c:if test="${not empty cliente}">
									<option value="${cliente.cidade.id}" selected>${cliente.cidade.nome}</option>
								</c:if>
								<c:if test="${empty cliente}">
									<option value="" selected disabled>Selecione uma cidade</option>
								</c:if>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/ClientesServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
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