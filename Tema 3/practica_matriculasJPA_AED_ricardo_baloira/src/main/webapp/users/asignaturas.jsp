<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Asignaturas</title>
</head>
<body>
	<h1>Lista de asignaturas</h1>
	<ul>
		<c:forEach items="${listaAsignaturas}" var="asignatura">
			<li>
				<a href="../gestionAsignatura?idasignatura=${asignatura.getIdasignatura()}"> ${asignatura.getNombre()} ${asignatura.getCurso()}</a>
			</li>
		</c:forEach>   
	</ul>
</body>
</html>