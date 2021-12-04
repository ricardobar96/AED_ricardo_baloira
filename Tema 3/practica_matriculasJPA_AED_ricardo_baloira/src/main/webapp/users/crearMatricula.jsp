<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title> Crear Matricula </title>
  <meta name="author" content="Ricardo Baloira Armas"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="css/matriculas_css.css"/>
</head>
<body>
	<h1>Crear Matricula</h1>
	<div class="formularios">
  		<div class="agregar">
    		<form name="formulario_agregar" action="../gestionMatricula" method="post">
		      <label for="dniMat_agregar">*DNI alumno: </label>
		      <input id="dniMat_agregar" type="text" name="dniMat_agregar" value="${dniCrear}" disabled/>
		      </br>
		      <label for="anioMat_agregar">*Anio: </label>
		      <input id="anioMat_agregar" type="text" name="anioMat_agregar"/>
		      </br>
		      <label for="asignMat_agregar">*Asignaturas: </label>
		      <input id="asignMat_agregar" type="text" name="asignMat_agregar"/>
		      </br>
		      </br>
		      </br>
		      </br>
		      <input id="boton_agregar" name="botonMatricula" type="submit" value="Agregar" style="width:20%"/>
  		  </form>
 		 </div>
 	  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
	</div>
	<div class="texto_gestion">
  		<textarea id="resultado" cols="150" rows="15" readonly>${textoMatricula}</textarea>
	</div>
</body>
</html>