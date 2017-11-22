<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.sanmi.citasClientes.hibernate.controller.Controlador"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar cita</title>
<link href="style2.css" rel="stylesheet" type="text/css">
</head>
<body>
	<% if (!Controlador.isLogin()){response.sendRedirect("login.jsp");} %>
	<h1 align="center">
		Modificar una cita
	</h1>
	<form id="modcita" action="modificar" method="post" accept-charset="UTF-8">		
		<table cellpadding="3pt" class="crear">
			<tr>
				<td class="data">Nota:</td>
				<td ><textarea rows="10" style="width:100%;" name="nota" form="modcita"><c:out value="${nota}"/></textarea></td>
			</tr>
			<tr>
				<td class="data">Data:</td>
				<td><input type="date" name="data" value="<c:out value="${data}" />"/></td>
			</tr>
		</table>
		<p align="center"><font color="red"><c:out value="${error}"/></font></p>
		<div style="margin: 0 auto; text-align: center; margin-top:20px">
			<button title="Cancelar" action="llistar_clientes"><img src="resources/cancel.png" alt=""></button>
			<input type="hidden" value="<c:out value="${id}" />" name="id" /> 
			<input type="hidden" value="<c:out value="${cliente_id}" />" name="cliente_id" />
			<button title="Modificar cita" type="submit" value="Modificar" name="modificar_cita"><img src="resources/tick.png" alt=""></button>
		</div>
	</form>
</body>
</html>