<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Detalhes Atendimento</title>
</head>
<body>
	<c:if test="${loginBean.nome == null }">	
		<jsp:forward page="index.jsp"> 
			<jsp:param name="msg" value="Usuario deve se autenticar para acessar o sistema" /> 
		</jsp:forward> 
	</c:if>
	<c:if test="${loginBean.nome != null }">
	<c:set var="atendimento" scope="request" value="${atendimento}"/>
		<div class="flex-container">
			<div class="form-cliente">
				<form class="purple-box" method="post" class="form-group">
					<h2 class="form-title">Detalhes Atendimento</h2>
					<div class="row">
						<input type="hidden" name="usuario" value="${loginBean.id}"/>
						<div class="col-md-6">
							Tipo de atendimento
							<select name="tipoAtendimento" disabled class="form-control">
								<option value="" selected disabled>Selecione um tipo</option>
								<c:forEach items="${tiposAtendimento}" var="tipo">
									<option value="${tipo.id}" selected="${atendimento.tipoAtendimento.id == tipo.id}">${tipo.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							Data/Hora: <input type="text" name="dtHr" value="${atendimento.dataHora}" disabled class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							Descri��o: <textarea type="text" name="desc" rows="6" disabled class="form-control" required>${atendimento.desc}</textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Produto
							<select name="produto" disabled class="form-control">
								<option value="" selected disabled>Selecione um produto</option>
								<c:forEach items="${produtos}" var="produto">
									<option value="${produto.id}" selected="${atendimento.produto.id == produto.id}">${produto.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							Cliente
							<select name="cliente" disabled class="form-control">
								<option value="" selected disabled>Selecione um produto</option>
								<c:forEach items="${clientes}" var="cliente">
									<option value="${cliente.id}" selected="${atendimento.cliente.id == cliente.id}">${cliente.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
						Foi resolvido?
							<div>
								<input type="radio" name="res" ${atendimento.res == 'S' ? 'checked' : ''}/><span class="mleft-10">Sim</span>
							</div>
							<div>
								<input type="radio" name="res" ${atendimento.res == 'N' ? 'checked' : ''}/><span class="mleft-10">N�o</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/AtendimentoServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:if>	
</body>
</html>