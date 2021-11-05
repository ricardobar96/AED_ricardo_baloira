<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
  <title> asignaturas.html </title>
  <meta name="author" content="Ricardo Baloira Armas"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="css/asignaturas_css.css"/>
</HEAD>
<BODY>
<h1>Gestionar Instituto</h1>
<h3>Asignaturas</h3>
<div class="formularios">
  <div class="agregar">
    <h4>Agregar asignatura</h4>
    <form name="formulario_agregar" action="consultas.php" method="post">
      <label for="id_agregar">*ID asignatura: </label>
      <input id="id_agregar" type="text"/>
      </br>
      <label for="nombreAsign_agregar">*Nombre: </label>
      <input id="nombreAsign_agregar" type="text"/>
      </br>
      <label for="dni_agregar">*DNI alumno: </label>
      <input id="dni_agregar" type="text"/>
      </br>
      <label for="anio_agregar">*Año: </label>
      <input id="anio_agregar" type="text"/>
      </br>
      <input id="boton_agregar" type="submit" value="Agregar" style="width:90%"/>
    </form>
  </div>

  <div class="borrar">
    <h4>Borrar asignatura</h4>
    <form name="formulario_borrar" action="consultas.php" method="post">
      <label for="id_borrar">*ID asignatura: </label>
      <input id="id_borrar" type="text"/>
      </br>
      <input id="boton_borrar" type="submit" value="Borrar" style="width:90%"/>
    </form>
  </div>

  <div class="editar">
    <h4>Editar asignatura</h4>
    <form name="formulario_editar" action="consultas.php" method="post">
      <label for="id_editar">*ID asignatura: </label>
      <input id="nombre_editar" type="text"/>
      </br>
      <label for="nombreAsign_editar">*Nombre: </label>
      <input id="nombreAsign_editar" type="text"/>
      </br>
      <label for="anio_editar">*Año: </label>
      <input id="anio_editar" type="text"/>
      </br>
      <input id="boton_editar" type="submit" value="Editar" style="width:90%"/>
    </form>
  </div>

  <div class="mostrar">
    <h4>Mostrar asignaturas:</h4>
    <h5>(escribir en uno de los campos únicamente)</h5>
    <form name="formulario_mostrar" action="consultas.php" method="post">
      <label for="anio_mostrar">Año: </label>
      <input id="anio_mostrar" type="text"/>
      </br>
      <label for="dni_mostrar">DNI alumno: </label>
      <input id="dni_mostrar" type="text"/>
      </br>
      <input id="boton_mostrar" type="submit" value="Mostrar" style="width:90%"/>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
</div>
</BODY>
</HTML>