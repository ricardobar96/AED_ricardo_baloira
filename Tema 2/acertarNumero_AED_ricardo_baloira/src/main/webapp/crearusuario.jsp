<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="author" content="ricardo baloira"/>
<title>Crear usuario</title>
</head>
<body>
	<h1 class="cabecera">CREAR USUARIO</h1>
	<form action="Principal" method="GET"> 
		<label for="nombre">Nombre: </label>
	   	<input type="text" name="nombre" id="nombre" required>
	   	<input type="submit" value="Enviar">
    </form>	
</body>
</html>