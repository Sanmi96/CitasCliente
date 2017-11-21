<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@page import="com.sanmi.citasClientes.hibernate.bean.Cita"%>
<%@page import="java.util.List"%>
<%@page import="com.sanmi.citasClientes.hibernate.controller.Controlador"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clientes</title>
<link href="style2.css" rel="stylesheet" type="text/css">  
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
	<% if (!Controlador.isLogin()){response.sendRedirect("login.jsp");} %>	
	<form action="logout"><p align="right"><button type="submit"><img src="resources/logout.png" alt=""></button></p> </form>
	<h1 align="center">Clientes</h1>
	<form action="crear_cliente" method="get">
		<table class="buscar">
			<thead>
				<tr >
					<td style="text-align: center;"><b>Nombre</b></td>
					<td style="text-align: center;"><b>Primer apellido</b></td>
					<td style="text-align: center;"><b>Segundo apellido</b></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input style='width:100%;' type="text" name="nombre" /></td>
					<td><input style='width:100%;' type="text" name="primer_apellido" /></td>
					<td><input style='width:100%;' type="text" name="segundo_apellido" /></td>
				</tr>
			</tbody>
		</table>
		<p align="center"><font color="red"><c:out value="${error}"/></font></p>
		<div style="margin: 0 auto; text-align: center; margin-bottom: 50px;">
			<button type="submit" value="Crear" name=crear_button ><img src="resources/add32.png" alt=""></button> <button type="submit"
				formaction="llistar_clientes" name="buscar_button" value="Buscar" ><img src="resources/search.png" alt=""></button>
		</div>
	</form>

	<hr>
	<div>
		<table class="clientes">  
			<thead>
				<tr>
					<td class="boto"></td>
					<td class="nom" style="text-align: center;"><b>Nombre</b></td>
					<td class="nom" style="text-align: center;"><b>Primer Apellido</b></td>
					<td class="nom" style="text-align: center;"><b>Segundo Apellido</b></td>
					<td class="boto"></td>
				</tr>
			</thead>
		</table>

		<c:forEach items="${clientes}" var="cliente" varStatus="loop">  

			<table class="clientes">
				<tbody>
					<tr>
						<td class="boto"><button
								id="citesBut<c:out value="${loop.count}"/>"
								onclick="showDiv(<c:out value="${loop.count}"/>,<c:out value="${cliente.id}"/>);" /><img src="resources/arrow-closed.png" alt=""></td>
						<td class="nom"><c:out value="${cliente.nombre}" /></td>
						<td class="nom"><c:out value="${cliente.primer_apellido}" /></td>
						<td class="nom"><c:out value="${cliente.segundo_apellido}" /></td>
						<td class="migboto">
							<form action="modificar" method="get">
							
								<input type="hidden" value="<c:out value="${cliente.id}" />" name="id" /> 
								<input type="hidden" value="<c:out value="${cliente.nombre}" />" name="nombre" />
								<input type="hidden" value="<c:out value="${cliente.primer_apellido}" />" name="primer_apellido" />
								<input type="hidden" value="<c:out value="${cliente.segundo_apellido}" />" name="segundo_apellido" /> 
								<button type="submit" value="modificar cliente" name="modificar_cliente"><img src="resources/edit.png" alt=""></button>
															
							</form>
						</td>
						<td class="migboto"> 
						<form action="esborrar" method="post">							
								<input type="hidden" value="<c:out value="${cliente.id}" />" 
									name="id" /> <button type="submit" value="borrar cliente"
									onclick="return confirm('Seguro que quieres dar de baja ese cliente?\n<c:out value="${cliente.nombre}" /> <c:out value="${cliente.primer_apellido}" /> <c:out value="${cliente.segundo_apellido}" />');" ><img src="resources/trash.png" alt=""></button>
						</form>	 
						</td>
					</tr>
				</tbody>
			</table>
			<div id="citesDiv<c:out value="${loop.count}"/>"
				style="display: none; margin: auto;">
				<table class="cites">
					<thead>
						<tr>
							<td class="data" style="text-align: center;"><b>Data</b></td>
							<td class="nota" style="text-align: center;"><b>Nota</b></td>
							<td class="boto">
								<form action="crear_cita" method ="get">
									<input type="hidden" value="<c:out value="${cliente.id}" />"
									name="cliente_id" />
									<input type="hidden" value="<c:out value="${cliente.nombre}" /> <c:out value="${cliente.primer_apellido}" /> <c:out value="${cliente.segundo_apellido}" />" name="nombre"/>
									<button type="submit" name="crear_button" value="nueva cita"><img src="resources/add.png" alt=""></button>
								</form>
							</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${cliente.cites}" var="cita">
							<tr>
								<td class="data"><c:out value="${cita.data}" /></td> 
								<td class="nota"><c:out value="${cita.nota}" /></td>
								<td class="boto">
								<div class="floating-box">
									<form action="modificar" method="get">
										<input type="hidden" value="<c:out value="${cita.id}" />" name="id" /> 
										<input type="hidden" value="<c:out value="${cita.cliente_id}" />" name="cliente_id" />
										<input type="hidden" value="<c:out value="${cita.nota}" />" name="nota" />
										<input type="hidden" value="<c:out value="${cita.data}" />" name="data" />
										<button type="submit" value="modificar cita" name="modificar_cita"><img src="resources/edit.png" alt=""></button>									
									</form>
								</div>
								<div class="floating-box">
									<form action="esborrar" method="POST">
										<input type="hidden" value="<c:out value="${cita.id}"/>" name="id"/>
										<button type="submit" value="borrar cita" name="borrar_cita" onclick="return confirm('Seguro que quieres borrar esa cita?\n<c:out value="${cita.nota}" />');"><img src="resources/trash.png" alt=""></button>
									</form>
								</div> 
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>

	</div>


	<script>	
        function showDiv(i,id) {  	
            var divStr = "citesDiv".concat(i);
            var butStr = "citesBut".concat(i);  
            div = document.getElementById(divStr);
            button = document.getElementById(butStr);
            if (div.style.display == "none") {
                div.style.display = "block";
                div.style.margin = "auto";
                button.innerHTML = "<img src='resources/arrow-opened.png' alt=''>";
            }
            else {
                div.style.display = "none";
                button.innerHTML = "<img src='resources/arrow-closed.png' alt=''>";
            }
        }
        
        
    </script>
</body>
</html>