<%@page import="com.acme.maven.controller.Acciones"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<body>
<h2>Hello World!</h2>

<form action="${pageContext.request.contextPath}/saludo" method="post">
	<input type="text" name="nombre" placeholder="intro nom" required>
	<button type="submit" value="dale">
</form>


<a href="canciones?accion=<%=Acciones.LISTAR%>">Listar Canciones</a>
<!-- 
<a href="${pageContext.request.contextPath}/canciones">ACCIONES canciones</a>
 



ANTES
<a href="${pageContext.request.contextPath}/canciones/listar">Ver todas las canciones</a>

<a href="${pageContext.request.contextPath}/canciones/crear">Crear cancion</a>


<a href="${pageContext.request.contextPath}/usuario/listar">Ver todos los users</a>

<a href="${pageContext.request.contextPath}/usuario/crear">Crear usuario</a>
-->


</body>
</html>





