<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	Anio: ${year}
	<br>
	Alumno:  ${alumnoMat}
	<br>
	Asignaturas:
	<br>
	<ul>
		<c:forEach items="${asignaturas}" var="asignatura">
			<li>
				<a href="./gestionAsignatura?idasignatura=${asignatura.getIdasignatura()}"> ${asignatura.getNombre()} ${asignatura.getCurso()}</a>
			</li>
		</c:forEach>   
	</ul>
</body>
</html>