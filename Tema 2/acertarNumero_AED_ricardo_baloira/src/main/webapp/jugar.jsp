<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="author" content="ricardo baloira"/>
<title>Insert title here</title>
</head>
<body>
	<h1 class="cabecera">JUGAR</h1>
	<form action="Apostar" method="POST"> 
		<label for="apuesta">Apuesta: </label>
	   	<input type="text" name="apuesta" id="apuesta" required>
	   	<br>
	   	<br>
	   	<input type="submit" value="Apostar">
    </form>	
    <br>
    <p>Hora del actual secreto: ${horaGenerada}</p>
    <p>Número introducido: ${numeroIntroducido}    ${jugador}</p>
</body>
</html>