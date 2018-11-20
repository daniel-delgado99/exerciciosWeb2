<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Cadastro de Usuário</title>
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
			<form class="" onsubmit="return validaForm()" action="${not empty usuario ? 'UsuarioServlet?action=update' : 'UsuarioServlet?action=new'}" method="post" class="form-group">
				<c:if test="${not empty usuario}">
					<input type="hidden" name="id" value="${usuario.id}"/>
					<h2 class="form-title">Alterar usuário</h2>
				</c:if>
				<c:if test="${empty usuario}">
					<h2 class="form-title">Novo usuário</h2>
				</c:if>
				<div class="row">
					<div class="col-md-6">
						CPF: <input  type="text" name="cpf" id="cpf" value="${not empty usuario ? usuario.cpf : ''}" ${not empty usuario ? 'disabled' : ''} class="form-control" required/>
					</div>
					<div class="col-md-6">
						Nome: <input type="text" name="nome" value="${not empty usuario ? usuario.nome : ''}" class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Email <input type="email" name="email" value="${not empty usuario ? usuario.email : ''}" ${not empty usuario ? 'disabled' : ''} class="form-control" required/>
					</div>
					
					<fmt:formatDate pattern="yyyy-MM-dd" value="${usuario.data}" var="dataFormatada"/>

					<div class="col-md-6">
						Data de nascimento <input type="date" name="data" value="${dataFormatada}" class="form-control" required/>
					</div>
				</div>
				<c:if test="${empty usuario}">
					<div class="row">
						<div class="col-md-6">
							Senha <input type="password" name="senha" class="form-control" required/>
						</div>
					</div>
				</c:if>
				<c:if test="${empty usuario || (not empty usuario && usuario.tipoUsuario.id != 1)}">
				<div class="row">
					<div class="col-md-12">
						Tipo de Usuário
						<select name="tipoUsuario" ${not empty usuario && usuario.id == login.id ? 'disabled' : ''} class="form-control">
							<option value="" disabled>Selecione um tipo de usuario</option>
							<option value="2" ${not empty usuario && usuario.tipoUsuario.id == 2 ? 'selected' : ''}>Funcionario</option>
							<option value="3" ${not empty usuario && usuario.tipoUsuario.id == 3 ? 'selected' : ''}>Gerente</option>
						</select>
					</div>
				</div>
				</c:if>
				<c:if test="${not empty usuario && usuario.tipoUsuario.id == 1}">
					<input type="hidden" name="tipoUsuario" value="1"/>
				</c:if>
				
				<div class="row">
					<div class="col-md-6">
						Rua <input type="text" name="rua" value="${not empty usuario ? usuario.rua : ''}" class="form-control" required/>
					</div>
					<div class="col-md-2">
						Número <input type="number" name="nr" value="${not empty usuario ? usuario.nr : ''}" class="form-control" required/>
					</div>
					<div class="col-md-4">
						CEP <input type="text" name="cep" id="cep" value="${not empty usuario ? usuario.cep : ''}" class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Estado
						<select id="estado" name="estado" class="form-control">
							<option value="" selected disabled>Selecione um estado</option>
							<c:forEach items="${estados}" var="estado">
								<option value="${estado.id}" ${not empty usuario && usuario.cidade.estado.id == estado.id ? 'selected' : ''}><c:out value="${estado.nome}"/></option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-6">						
						Cidade
						<select id="cidade" name="cidade" class="form-control">
							<c:if test="${not empty usuario}">
								<c:forEach items="${cidades}" var="cidade">
									<option value="${cidade.id}" ${usuario.cidade.id == cidade.id ? 'selected' : ''}><c:out value="${cidade.nome}"/></option>
								</c:forEach>
							</c:if>
							<c:if test="${empty usuario}">
								<option value="" selected disabled>Selecione uma cidade</option>
							</c:if>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href='${pageContext.request.contextPath}/UsuarioServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
					</div>
					<div class="col-md-6">
						<button class="btn btn-success btn-new" type="submit">Enviar</button>
					</div>
				</div>
			</form>
		</div>
	</c:if>	
</body>
</html>