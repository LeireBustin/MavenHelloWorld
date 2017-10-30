<%@page import="com.acme.maven.modelo.pojo.Cancion" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>

<!DOCTYPE html>

<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link rel="icon" href="${pageContext.request.contextPath}/img/favicon.png">

    <title>Menu canciones</title>

    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>

<body>
	<%
	
	ArrayList<Cancion> canciones = (ArrayList<Cancion>)request.getAttribute("canciones");
	/*
	   Iterator cans = canciones.iterator();	//cogemos los keys con Iterator
	    while(cans.hasNext()) {
	    	String cancion = (String)cans.next();
	    	out.println( cancion + " = (" + request.getParameter(cancion) + ")");
	    }
	   */
	    Iterator itr = canciones.iterator();
	      
	      while(itr.hasNext()) {
	         Cancion cancion = (Cancion)itr.next();
	         System.out.print(cancion + " ");
	      } 
	/*
	Iterator<Cancion> iterator = canciones.iterator();
	while (iterator.hasNext()) {
		
		iterator.next().toString();
		
	}
	*/
	/*
	System.out.println("#2 advance for loop");
	for (String cancion : canciones) {
		System.out.println(cancion);
	}
	*/
	
	/*
	for (Cancion c : canciones){
		cancion.getNombre()
		
		*/
	%>
	
	${msg}
	
	<%
	/*
	}
	*/
	
	Cancion c = (Cancion)request.getAttribute("c");
	 %>
	 
	<p><%=c.getNombre() %></p><br>
	<p><%=c.getArtista() %></p>
	<p><%=c.getDuracion() %></p>

	
<% 
	//c:if test="${empty canciones}

	//para ponerlo en html, en el JSP	<!-- ${c.toString} COMO PONER ESTO CON EXPRESION LANGUAGE?-->
%>	
	<!-- ${canciones}	 -->
	
</body>


</html>