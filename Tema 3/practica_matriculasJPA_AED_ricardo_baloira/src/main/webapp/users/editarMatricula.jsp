<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar matricula</title>
  <meta name="author" content="Ricardo Baloira Armas"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="css/matriculas_css.css"/>
</head>
<body>
<h1>Editar Matricula</h1>
	<div class="formularios">
	<div class="editar">
    <h4>Editar matrícula</h4>
    <form name="formulario_editar" action="../gestionMatricula" method="post">
      <label for="idMat_editar">*ID matrícula: </label>
      <input id="idMat_editar" type="text" name="idMat_editar" value="${idEditar}" disabled/>
      </br>
      <label for="dniMat_editar">*DNI alumno: </label>
      <input id="dniMat_editar" type="text" name="dniMat_editar" value="${dniCrear}" disabled/>
      </br>
      <label for="anioMat_editar">*Año: </label>
      <input id="anioMat_editar" type="text" name="anioMat_editar"/>
      </br>
      <label for="asignMat_editar">*Asignaturas: </label>
      <input id="asignMat_editar" type="text" name="asignMat_editar"/>
      </br>
      <input id="boton_editar" name="botonMatricula" type="submit" value="Editar" style="width:20%"/>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
	</div>
	<div class="texto_gestion">
  		<textarea id="resultado" cols="150" rows="15" readonly>${textoMatricula}</textarea>
	</div>
</body>
</html>