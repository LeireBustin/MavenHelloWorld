<%@page import="com.acme.maven.controller.Acciones"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:choose>
	<c:when test="${ usuario.id == -1 }"><h1>Crear nuevo usuario</h1></c:when>
	<c:otherwise><h1>Detalle ${usuario.nombre}</h1></c:otherwise>
</c:choose>

<form action="usuarios" method="post">
<!-- CAMBIAR A REGISTRO Y LOGIN -->
	<!-- campos ocultos -->
	<input type="hidden" name="id" value="${usuario.id}">
	<input type="hidden" name="accion" value="<%=Acciones.CREAR_MODIFICAR%>">
	
	<!-- campos editables por el usuario -->
	<label for="nombre">Nombre:</label>
	<input type="text" name="nombre" value="${usuario.nombre}" required>
	<br>
	
	<label for="pass">Password:</label>
	<input type="text" name="pass" value="${usuario.pass}" required>
	<br>
	
	<label for="email">Email:</label>
	<input type="text" name="email" value="${usuario.email}" required>
	<br>
	
	<label for="avatar">Avatar:</label>
	<input type="text" name="avatar" value="${usuario.avatar}">
	<br>
	
	
	<!-- boton submit -->
	<input type="submit" value="MODIFICAR">
</form>