<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alumnos</title>
</head>
<body>
	<h1>Lista de alumnos</h1>
	<ul>
		<c:forEach items="${listaAlumnos}" var="alumno">
			<li>
				<a href="../gestionAlumno?dni=${alumno.getDni()}"> ${alumno.getNombre()} ${alumno.getApellidos()}</a>
			</li>
		</c:forEach>   
	</ul>
</body>
</html>