<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.sanmi.citasClientes.hibernate.controller.Controlador"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modificar cliente</title>
<link href="style2.css" rel="stylesheet" type="text/css">
</head>
<body>
	<% if (!Controlador.isLogin()){response.sendRedirect("login.jsp");} %>
	<h1 align="center">
		Modificar los datos del cliente
	</h1>
	<form action="modificar" method="post" accept-charset="UTF-8">		
		<table cellpadding="3pt" class="crear">
			<tr>
				<td class = "data">Nombre:</td>
				<td><input type="text" name="nombre" style="width:100%;" value="<c:out value="${nombre}" />"/></td>
			</tr>
			<tr>
				<td class = "data">Primer apellido:</td>
				<td><input type="text" name="primer_apellido" style="width:100%;" value="<c:out value="${primer_apellido}" />"/></td>
			</tr>
			<tr>
				<td class = "data">Segundo apellido:</td>
				<td><input type="text" name="segundo_apellido" style="width:100%;" value="<c:out value="${segundo_apellido}" />"/></td>
			</tr>
		</table>
		<p align="center"><font color="red"><c:out value="${error}"/></font></p>
		<div style="margin: 0 auto; text-align: center; margin-top:20px">				
				<input type="hidden" value="<c:out value="${id}" />" name="id" />
				<button title="Cancelar" type="submit" value="Cancelar"><img src="resources/cancel.png" alt=""></button>
				<button title="Modificar cliente" type="submit" value="Modificar" name="modificar_cliente"><img src="resources/tick.png" alt=""></button>								
			
		</div>
	</form>
</body>
</html>