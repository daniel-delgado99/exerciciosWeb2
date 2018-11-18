<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Auto Cadastro</title>
</head>
<body>
	<div class="flex-container">
		<div class="form-cliente">
			<form class="purple-box" action="UsuarioServlet?action=new" method="post" class="form-group">
				<input type="hidden" name="tipoUsuario" value="1"/>
				<h2 class="form-title">Novo usuário</h2>
			
				<div class="row">
					<div class="col-md-6">
						CPF: <input  type="text" name="cpf" id="cpf" class="form-control" required/>
					</div>
					<div class="col-md-6">
						Nome: <input type="text" name="nome"  class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Email <input type="email" name="email" class="form-control" required/>
					</div>
					<div class="col-md-6">
						Data <input type="date" name="data" class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Senha <input type="password" name="senha" class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Rua <input type="text" name="rua" class="form-control" required/>
					</div>
					<div class="col-md-2">
						Numero <input type="number" name="nr" class="form-control" required/>
					</div>
					<div class="col-md-4">
						CEP <input type="text" name="cep" id="cep" class="form-control" required/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						Estado
						<select id="estado" name="estado" class="form-control">
							<option value="" selected disabled>Selecione um estado</option>
							<c:forEach items="${estados}" var="estado">
								<option value="${estado.id}"><c:out value="${estado.nome}"/></option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-6">						
						Cidade
						<select id="cidade" name="cidade" class="form-control">
							<option value="" selected disabled>Selecione uma cidade</option>
						</select>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href='${pageContext.request.contextPath}/index.jsp' class="btn btn-warning btn-new" type="button">Cancelar</a>
					</div>
					<div class="col-md-6">
						<button class="btn btn-success btn-new" type="submit">Enviar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>