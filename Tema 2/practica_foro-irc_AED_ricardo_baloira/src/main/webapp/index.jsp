<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
		<meta charset="utf-8">
		<meta name="author" content="ricardo baloira">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="refresh" content="20">
		<title>foro web</title>	
	    <link rel="stylesheet" href="foro%20web_files/estilos.css">
	</head>
   	<body>
   		<h1 class="cabecera">IRC Foro</h1>
		<div class="principal">
	   		<div class="foro">
            	<c:forEach items="${mensajeforo}" var="linea">
			    	<c:out value="${linea}"/> <br>
				</c:forEach>         
	   		</div>		   		
	   			<ul class="conectados">	
	   			</ul>	
	   		<form action="Principal" method="POST"> 
	   			<label for="nombre">nombre: </label>
	   			<input type="text" name="nombre" id="nombre">
	   			<textarea name="mensaje" id="mensaje" cols="20" rows="10"></textarea>
	   			<input type="submit" value="Enviar">
	   		</form>		
		</div> 	
</body>
</html>
