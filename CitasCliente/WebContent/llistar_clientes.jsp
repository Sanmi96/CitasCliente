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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/session.js"></script>
<script type="text/javascript" >	
	var divs_oberts = Session.get("divs_oberts");
	if (typeof divs_oberts === 'undefined'){
	    divs_oberts = [];
	}	
	</script>	
</head>

<body onload="checkSession();" >
	<% if (!Controlador.isLogin()){response.sendRedirect("login.jsp");} %> 
	
	<form action="logout"><p align="right"><button title="Cerrar sesión" type="submit"><img src="resources/logout.png" alt=""></button></p> </form>
	<h1 align="center">Clientes</h1>
	<form action="crear_cliente" method="get">
		<table class="buscar">
			<thead>
				<tr>
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
			<button title="Crear cliente" onclick="clearSession();" type="submit" value="Crear" name=crear_button ><img src="resources/add32.png" alt=""></button> <button title="Buscar clientes" type="submit"
				onclick="clearSession();" formaction="llistar_clientes" name="buscar_button" value="Buscar" ><img src="resources/search.png" alt=""></button>
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
		
			<div id="div_cliente_id<c:out value="${loop.count}"/>" style="display:none;"><c:out value="${cliente.id}" /></div>	
			
			<table class="clientes">
				<tbody>
					<tr>
						<td class="boto"><button title="Ver citas"
								id="citesBut<c:out value="${loop.count}"/>"
								onclick="showDiv(<c:out value="${loop.count}"/>,<c:out value="${cliente.id}"/>,true);" /><img src="resources/arrow-closed.png" alt=""></td>
						<td class="nom"><c:out value="${cliente.nombre}" /></td>
						<td class="nom"><c:out value="${cliente.primer_apellido}" /></td>
						<td class="nom"><c:out value="${cliente.segundo_apellido}" /></td>
						<td class="migboto">
							<form action="modificar" method="get">
							
								<input type="hidden" value="<c:out value="${cliente.id}" />" name="id" /> 
								<input type="hidden" value="<c:out value="${cliente.nombre}" />" name="nombre" />
								<input type="hidden" value="<c:out value="${cliente.primer_apellido}" />" name="primer_apellido" />
								<input type="hidden" value="<c:out value="${cliente.segundo_apellido}" />" name="segundo_apellido" /> 
								<button title="Modificar datos" onclick="clearSession();" type="submit" value="modificar cliente" name="modificar_cliente"><img src="resources/edit.png" alt=""></button>
															
							</form>
						</td>
						<td class="migboto"> 
						<form action="esborrar" method="post">							
								<input type="hidden" value="<c:out value="${cliente.id}" />" 
									name="id" /> <button title="Borrar cliente" type="submit" value="borrar cliente"
									onclick="return confirm('Seguro que quieres dar de baja ese cliente?\n<c:out value="${cliente.nombre}" /> <c:out value="${cliente.primer_apellido}" /> <c:out value="${cliente.segundo_apellido}" />'); clearSession();" ><img src="resources/trash.png" alt=""></button>
						</form>	 
						</td>
					</tr>
				</tbody>
			</table>
			<div id="citesDiv<c:out value="${loop.count}"/>"
				style="display: none; margin: auto;">
				<table id="tcitas" class="citeshead">
					<thead>
						<tr>
							<td class="data" style="text-align: center;"><b>Fecha</b></td>
							<td class="nota" style="text-align: center;"><b>Nota</b></td>
							<td class="boto">
								<form action="crear_cita" method ="get">
									<input type="hidden" value="<c:out value="${cliente.id}" />"
									name="cliente_id" />
									<input type="hidden" value="<c:out value="${cliente.nombre}" /> <c:out value="${cliente.primer_apellido}" /> <c:out value="${cliente.segundo_apellido}" />" name="nombre"/>
									<button title="Nueva cita" onclick="saveSession();" type="submit" name="crear_button" value="nueva cita"><img src="resources/add.png" alt=""></button>
								</form>
							</td>
						</tr>
					</thead>	
				</table>				
				<div id="div_cites<c:out value="${loop.count}"/>"></div>
				
					
			</div>
		</c:forEach>

	</div>	
	
	<script>
		//Remove obj from array function
		Array.prototype.remove = function() {
	    	var what, a = arguments, L = a.length, ax;
	    	while (L && this.length) {
	        	what = a[--L];
	        	while ((ax = this.indexOf(what)) !== -1) {
	            	this.splice(ax, 1);
	        	}
	    	}
	    	return this;
		}
		
		function checkSession(check){ 
		    for (i = 0; i < divs_oberts.length; i++) {
				var div_str = "div_cliente_id".concat(divs_oberts[i]);
				div = document.getElementById(div_str);
				showDiv(divs_oberts[i], div.innerHTML,false);
			}		    
		}
		
		function clearSession(){
		    Session.clear();
		    divs_oberts = [];
		}
		
		function saveSession(){
		    Session.set("divs_oberts",divs_oberts);
		}		
		
        function showDiv(i, cliente_id, save) {             
            var divStr = "citesDiv".concat(i);
            var butStr = "citesBut".concat(i);
            div = document.getElementById(divStr);
            button = document.getElementById(butStr);
            if (div.style.display == "none") {
        	if (document.getElementById("div_cites".concat(i)).innerHTML === ""){
        	$.ajax({
        	    type:"POST",
        	    url: "llistar_citas",
        	    data: {cliente_id:cliente_id},
        	    success:function(responsexml){   
        			document.getElementById("div_cites".concat(i)).innerHTML = responsexml;
        		}
        	}) 
        	}
        		if (save) {divs_oberts.push(i);}
                div.style.display = "block";         
        		div.style.margin = "auto";
                button.innerHTML = "<img src='resources/arrow-opened.png' alt=''>";
            }
            else {        		
                div.style.display = "none";
                button.innerHTML = "<img src='resources/arrow-closed.png' alt=''>"; 
                if (save) {divs_oberts.remove(i);}
            }
        } 
        
    </script>
</body>
</html>