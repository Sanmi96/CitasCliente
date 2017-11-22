<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Autenticación de usuario</title>
<link href="style2.css" rel="stylesheet" type="text/css">
</head>
<body>	
		<form action="login" method="post">  
			
			<table style="width:400px;" cellpadding="3pt"> 
				<tr>
					<td class = "data">Usuario: </td> 	
					<td class = "login"><input style="width:100%;" type="text" name="nombre" autofocus/></td>		
				</tr>
				<tr>
					<td class = "data">Contraseña: </td> 
					<td class = "login"><input style="width:100%;" type="password" name="password"/></td>
				</tr>
			</table>
			<p align="center"><font color="red"><c:out value="${error}"/></font></p>
				
			<div style="margin: 0 auto; text-align: center; margin-top:20px">
				<button title="Autenticarse" type="submit" value="submit"><img src="resources/login.png" alt=""></button>
			</div>
		</form>
		
</body>
</html>