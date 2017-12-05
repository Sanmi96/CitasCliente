<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.sanmi.citasClientes.hibernate.bean.Cita"%>
<%@page import="java.util.List"%>
<table class="cites">
	<c:forEach items="${citas}" var="cita">
		<tr>
			<td class="data"><c:out value="${cita.data}" /></td>
			<td class="nota"><c:out value="${cita.nota}" /></td>
			<td class="boto">
				<div class="floating-box">
					<form action="modificar" method="get">
						<input type="hidden" value="<c:out value="${cita.id}" />"
							name="id" /> <input type="hidden"
							value="<c:out value="${cita.cliente_id}" />" name="cliente_id" />
						<input type="hidden" value="<c:out value="${cita.nota}" />"
							name="nota" /> <input type="hidden"
							value="<c:out value="${cita.data}" />" name="data" />
						<button title="Modificar datos" onclick="saveSession();" type="submit"
							value="modificar cita" name="modificar_cita">
							<img src="resources/edit.png" alt="">
						</button>
					</form>
				</div>
				<div class="floating-box">
					<form action="esborrar" method="POST">
						<input type="hidden" value="<c:out value="${cita.id}"/>" name="id" />
						<button title="Borrar cita" type="submit" value="borrar cita"
							name="borrar_cita"
							onclick="saveSession(); return confirm('Seguro que quieres borrar esa cita?\n<c:out value="${cita.nota}" />'); ">
							<img src="resources/trash.png" alt="">
						</button>
					</form>
				</div>
			</td>
		</tr>
	</c:forEach>
</table>

