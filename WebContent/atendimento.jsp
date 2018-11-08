<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Atendimento</title>
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
				<form class="purple-box" action="AtendimentoServlet?action=new" method="post" class="form-group">
					<h2 class="form-title">Novo Atendimento</h2>
					<div class="row">
						<input type="hidden" name="usuario" value="${loginBean.id}"/>
						<div class="col-md-6">
							Tipo de atendimento
							<select name="tipoAtendimento" class="form-control">
								<option value="" selected disabled>Selecione um tipo</option>
								<c:forEach items="${tiposAtendimento}" var="tipo">
									<option value="${tipo.id}">${tipo.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							Data/Hora: <input id="dataHora" type="text" name="dtHr" value="getCurrentDate()" class="form-control" required/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							Descrição: <textarea type="text" name="desc" rows="6" class="form-control" required></textarea>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							Produto
							<select name="produto" class="form-control">
								<option value="" selected disabled>Selecione um produto</option>
								<c:forEach items="${produtos}" var="produto">
									<option value="${produto.id}">${produto.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6">
							Cliente
							<select name="cliente" class="form-control">
								<option value="" selected disabled>Selecione um produto</option>
								<c:forEach items="${clientes}" var="cliente">
									<option value="${cliente.id}">${cliente.nome}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
						Foi resolvido?
							<div>
								<input type="radio" name="res" value="S"/><span class="mleft-10">Sim</span>
							</div>
							<div>
								<input type="radio" name="res" value="N"/><span class="mleft-10">Não</span>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6">
							<a href='${pageContext.request.contextPath}/AtendimentoServlet' class="btn btn-warning btn-new" type="button">Cancelar</a>
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