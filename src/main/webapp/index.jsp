<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<body>
<h2>Hello World!</h2>

<form action="saludo" method="post">
	<input type="text" name="nombre" placeholder="intro nom">
	<button type="submit" value="dale">
</form>

<a href="${pageContext.request.contextPath}/canciones/listar">Ver todas las canciones</a>

<a href="${pageContext.request.contextPath}/canciones/crear">Crear cancion</a>


<a href="${pageContext.request.contextPath}/usuario/listar">Ver todos los users</a>

<a href="${pageContext.request.contextPath}/usuario/crear">Crear usuario</a>

</body>
</html>
