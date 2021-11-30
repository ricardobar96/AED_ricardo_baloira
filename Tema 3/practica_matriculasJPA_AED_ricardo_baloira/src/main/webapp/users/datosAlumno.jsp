<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 style="text-align:center;">Datos del alumno</h1>
	<p>Nombre: ${nombre}</p>

	<p>Apellidos: ${apellidos}</p>

	<p>Fecha Nacimiento: ${fechaNac}</p>

	<p>DNI: ${dni}</p>

	<p>Matriculas:</p>
	<ul>
		<c:forEach items="${matriculas}" var="matricula">
			<li>
				<a href="./gestionMatricula?idmatricula=${matricula.getIdmatricula()}"> Id matricula: ${matricula.getIdmatricula()} || Anio: ${matricula.getYear()}</a>
			</li>
		</c:forEach>   
	</ul>
	<br>
	<p>Para crear una nueva matricula: <a href="users/crearMatricula.jsp">Crear matricula</a></p>
</body>
</html>