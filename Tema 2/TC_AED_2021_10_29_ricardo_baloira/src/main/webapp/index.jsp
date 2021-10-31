<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Poner el nombre del fichero y el texto que quieras guardar:</h1>
        <h2>(si no existe el fichero se crea uno nuevo. Si existe se sobreescribe)</h2>
        
        	<form action="Principal" method="POST"> 

	   			<label for="nombre">Nombre fichero: </label>
	   			<input type="text" name="nombre" id="nombre" value=${nombre}>
	   			<br>
	   			<textarea name="texto" id="texto" cols="50" rows="10"><%String texto = request.getParameter("texto");if(texto!=null){%><%= request.getParameter("texto") %><%}%></textarea>
	   			<input type="submit" value="enviar">                             
	   		</form>	
	   			
	   		<p>${guardado}</p>
	   		<br>
	   		<br>
	   		
	   	<h1>Puedes cargar un fichero para editarlo escribiendo el nombre aquí</h1>	
	   	<h2>(si no existe el fichero se crea nuevo)</h2>
	   	
	   		<form action="Principal" method="POST"> 

	   			<label for="nombreCargar">Nombre fichero: </label>
	   			<input type="text" name="nombreCargar" id="nombreCargar" value=${nombreCargar}>
	   			<input type="submit" value="cargar">
                                
	   		</form>	
    </body>
</html>
