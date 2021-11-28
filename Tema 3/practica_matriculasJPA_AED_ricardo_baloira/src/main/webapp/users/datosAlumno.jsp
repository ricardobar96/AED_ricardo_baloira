<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Nombre: ${nombre}
	<br>
	Apellidos: ${apellidos}
	<br>
	Fecha Nacimiento: ${fechaNac}
	<br>
	DNI: ${dni}
	<br>
	Matriculas:
	<br>
	<ul>
		<c:forEach items="${matriculas}" var="matricula">
			<li>
				<a href="../gestionAlumno?id=${matricula.getIdmatricula()}"> Id matricula: ${matricula.getIdmatricula()} || Anio: ${matricula.getYear()}</a>
			</li>
		</c:forEach>   
	</ul>
</body>
</html>