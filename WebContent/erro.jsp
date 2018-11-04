<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ include file="header.jsp" %>  
	<title>Erro</title>
</head>
<body>
	<div class="flex-container">
		<div class="purple-box login">
			<div class="col-md-12">
				<h3 style='color: red'>Ocorreu um erro</h3>
				<h3>Erro: ${pageContext.exception.message}</h3>
				<!--  <p>Stack Trace: ${pageContext.out.flush()}
								${pageContext.exception.printStackTrace(pageContext.response.writer)}
				</p> -->
				<div>
					<a class="btn btn-primary" href='${pageContext.request.contextPath}/<%= session.getAttribute("page") %>'>Inicio</a>
				</div>
				<div class="footer">
					<p>Em caso de problemas, contatar o adminstrador.</p>
					<p>${applicationScope['adminEmail']}</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>