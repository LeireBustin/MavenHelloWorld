<%@page import="com.acme.maven.controller.Acciones"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!--	${cancion}  -->

<c:choose>
	<c:when test="${ cancion.id == -1 }"><h1>Crear nueva cancion</h1></c:when>
	<c:otherwise><h1>Detalle ${cancion.nombre}</h1></c:otherwise>
</c:choose>

<form action="canciones" method="post">

	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${cancion.id}">
	<input type="hidden" name="accion" value="<%=Acciones.CREAR_MODIFICAR%>">
	
	<!-- campos editables x el user -->
	<label for="nombre" >Nombre:</label>
	<input type="text" name="nombre" value="${cancion.nombre}" required>
	<br>
	
	<label for="artista" >Artista:</label>
	<input type="text" name="artista" value="${cancion.artista}" required>
	<br>
	
	<label for="duracion" >Duracion:</label>
	<input type="text" name="duracion" value="${cancion.duracion}" required>
	<br>
	
	<label for="cover" >Cover:</label>
	<input type="text" name="cover" value="${cancion.cover}">
	
	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>

