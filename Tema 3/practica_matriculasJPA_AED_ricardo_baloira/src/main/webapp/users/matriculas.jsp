<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Matriculas</title>
</head>
<body>
	<h1>Lista de matriculas</h1>
	<ul>
		<c:forEach items="${listaMatriculas}" var="matricula">
			<li>
				<a href="../gestionMatricula?idmatricula=${matricula.getIdmatricula()}"> ${matricula.getAlumno().getNombre()} ${matricula.getYear()}</a>
			</li>
		</c:forEach>   
	</ul>
</body>
</html>