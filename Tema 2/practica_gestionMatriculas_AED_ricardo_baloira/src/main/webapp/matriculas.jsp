<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
  <title> matriculas.html </title>
  <meta name="author" content="Ricardo Baloira Armas"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="css/matriculas_css.css"/>
</HEAD>
<BODY>
<h1>Gestionar Instituto</h1>
<h3>Matrículas</h3>
<div class="formularios">
  <div class="agregar">
    <h4>Agregar matrícula</h4>
    <form name="formulario_agregar" action="gestionMatricula" method="post">
      <label for="dniMat_agregar">*DNI alumno: </label>
      <input id="dniMat_agregar" type="text" name="dniMat_agregar"/>
      </br>
      <label for="anioMat_agregar">*Año: </label>
      <input id="anioMat_agregar" type="text" name="anioMat_agregar"/>
      </br>
      <label for="asignMat_agregar">*Asignaturas: </label>
      <input id="asignMat_agregar" type="text" name="asignMat_agregar"/>
      </br>
      </br>
      </br>
      </br>
      <input id="boton_agregar" name="botonMatricula" type="submit" value="Agregar" style="width:90%"/>
    </form>
  </div>

  <div class="borrar">
    <h4>Borrar matrícula</h4>
    <form name="formulario_borrar" action="enviarinfo" method="post">
      <label for="idMat_borrar">*ID matrícula: </label>
      <input id="idMat_borrar" type="text" name="idMat_borrar"/>
      </br>
      </br>
      </br>
      </br>
      <input id="boton_borrar" name="botonMatricula" type="submit" value="Borrar" style="width:90%"/>
    </form>
  </div>

  <div class="editar">
    <h4>Editar matrícula</h4>
    <form name="formulario_editar" action="enviarinfo" method="post">
      <label for="idMat_editar">*ID matrícula: </label>
      <input id="idMat_editar" type="text" name="idMat_editar"/>
      </br>
      <label for="dniMat_editar">*DNI alumno: </label>
      <input id="dniMat_editar" type="text" name="dniMat_editar"/>
      </br>
      <label for="anioMat_editar">*Año: </label>
      <input id="anioMat_editar" type="text" name="anioMat_editar"/>
      </br>
      <label for="asignMat_editar">*Asignaturas: </label>
      <input id="asignMat_editar" type="text" name="asignMat_editar"/>
      </br>
      <input id="boton_editar" name="botonMatricula" type="submit" value="Editar" style="width:90%"/>
    </form>
  </div>

  <div class="mostrar">
    <h4>Mostrar matrículas:</h4>
    <h5>(escribir en uno de los campos únicamente)</h5>
    <form name="formulario_mostrar" action="enviarinfo" method="post">
      <label for="anioMat_mostrar">Año: </label>
      <input id="anioMat_mostrar" type="text" name="anioMat_mostrar"/>
      </br>
      <label for="dniMat_mostrar">DNI alumno: </label>
      <input id="dniMat_mostrar" type="text" name="dniMat_mostrar"/>
      </br>
      </br>
      </br>
      </br>
      <input id="boton_mostrar" name="botonMatricula" type="submit" value="Mostrar" style="width:90%"/>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
</div>
<div class="texto_gestion">
  <textarea id="resultado" name="textoMatricula" cols="150" rows="15"></textarea>
</div>
</BODY>
</HTML>