<%@page import="com.acme.maven.controller.Acciones"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p style="color:red;">${msg}</p>


<form action="canciones" method="get">
	<input type="hidden" name="accion" value="<%=Acciones.BUSCAR%>">
	<input type="search" name="criterio" placeholder="Buscar por nombre o artista" required>
	<button type="submit">BUSCAR</button>
</form>


<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>">CREAR CANCION</a>

<!-- TODO -->

<ul>
<c:forEach items="${canciones}" var="cancion">
	<li>
		${cancion.nombre}   
		<a href="canciones?accion=<%=Acciones.MOSTRAR_FORMULARIO%>&id=${cancion.id}">DETALLE</a>
		<a href="canciones?id=${cancion.id}&accion=<%=Acciones.ELIMINAR%>">***Eliminar***</a>
	</li>	
</c:forEach>
</ul>
