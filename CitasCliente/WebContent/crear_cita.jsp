<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.sanmi.citasClientes.hibernate.controller.Controlador"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crear Cita</title>
<link href="style2.css" rel="stylesheet" type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
</head>
<body>
	<% if (!Controlador.isLogin()){response.sendRedirect("login.jsp");} %>
	<h1 align="center">
		Nueva cita para
		<c:out value="${nombre}" />
	</h1>
	<form id="crearcita" action="crear_cita" method="post" accept-charset="UTF-8">
		<input type="hidden" value="<c:out value="${cliente_id}"/>"
			name="cliente_id" />
		<table cellpadding="3pt" class="crear">
			<tr>
				<td class="data">Nota:</td>
				<td><textarea rows="10" style="width:100%;" name="nota" form="crearcita"></textarea></td>
			</tr>
			<tr>
				<td class="data">Data:</td>
				<td><input id="data" type="date" name="data" /></td> 
			</tr>
		</table>
		<p align="center"><font color="red"><c:out value="${error}"/></font></p>
		<div style="margin: 0 auto; text-align: center; margin-top:20px">
			<button title="Cancelar" type="submit" value="Cancelar"><img src="resources/cancel.png" alt=""></button>
			<input type="hidden" value="<c:out value="${cliente_id}" />" name="cliente_id" />
			<input type="hidden" value="<c:out value="${nombre}" />" name="nombre"/>
			<button title="Crear cita" type="submit" value="Crear" name="crear_btn" ><img src="resources/tick.png" alt=""></button>
		</div>
	</form>
	
	<script>	
	$( document ).ready(function() {
	    var today = new Date();
	    var dd = today.getDate();
	    var mm = today.getMonth()+1; //January is 0!
	    var yyyy = today.getFullYear();
	    if(dd<10) {
	        dd = '0'+dd
	    } 
	    if(mm<10) {
	        mm = '0'+mm
	    } 
	    today = yyyy + "-" + mm + "-" + dd;
	    console.log(today);
	    document.getElementById("data").value = today;
	});
	</script>
</body>
</html>