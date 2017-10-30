<%@page import="com.acme.maven.controller.Acciones"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p style="color:red;">${msg}</p>


<form action="usuarios" method="get">
	<input type="hidden" name="accion" value="<%=Acciones.BUSCAR%>">
	<input type="search" name="criterio" placeholder="Buscar por nombre o artista" required>
	<button type="submit">BUSCAR</button>
</form>



<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">CREAR USUARIO</a>

<ul>
<c:forEach items="${usuarios}" var="usuario">
	<li>
		${usuario.nombre}   
		<a href="usuarios?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${usuario.id}">DETALLE</a>
		<a href="usuarios?id=${usuario.id}&accion=<%=Acciones.ELIMINAR%>">***Eliminar***</a>
	</li>	
</c:forEach>
</ul>
