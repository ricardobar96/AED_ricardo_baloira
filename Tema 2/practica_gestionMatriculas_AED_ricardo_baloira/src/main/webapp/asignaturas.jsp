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
    <form name="formulario_agregar" action="gestionAsignaturas" method="post">
      <label for="idAsign_agregar">*ID asignatura: </label>
      <input id="idAsign_agregar" type="text" nombre="idAsign_agregar"/>
      </br>
      <label for="nombreAsign_agregar">*Nombre: </label>
      <input id="nombreAsign_agregar" type="text" nombre="nombreAsign_agregar"/>
      </br>
      <label for="cursoAsign_agregar">*Curso: </label>
      <input id="cursoAsign_agregar" type="text" nombre="cursoAsign_agregar"/>
      </br>
      <input id="boton_agregar" name="botonAsignatura" type="submit" value="Agregar" style="width:90%"/>
    </form>
  </div>

  <div class="borrar">
    <h4>Borrar asignatura</h4>
    <form name="formulario_borrar" action="gestionAsignaturas" method="post">
      <label for="idAsign_borrar">*ID asignatura: </label>
      <input id="idAsign_borrar" type="text" name="idAsign_borrar"/>
      </br>
      <input id="boton_borrar" name="botonAsignatura" type="submit" value="Borrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>

  <div class="editar">
    <h4>Editar asignatura</h4>
    <form name="formulario_editar" action="gestionAsignaturas" method="post">
      <label for="idAsign_editar">*ID asignatura: </label>
      <input id="idAsign_editar" type="text" name="idAsign_editar"/>
      </br>
      <label for="nombreAsign_editar">*Nombre: </label>
      <input id="nombreAsign_editar" type="text" name="nombreAsign_editar"/>
      </br>
      <label for="cursoAsign_editar">*Curso: </label>
      <input id="cursoAsign_editar" type="text" name="cursoAsign_editar"/>
      </br>
      </br>
      </br>
      <input id="boton_editar" name="botonAsignatura" type="submit" value="Editar" style="width:90%"/>
    </form>
  </div>

  <div class="mostrar">
    <h4>Mostrar asignaturas:</h4>
    <h5>(escribir en uno de los campos únicamente)</h5>
    <form name="formulario_mostrar" action="gestionAsignaturas" method="post">
    <label for="nombreAsign_mostrar">Nombre: </label>
      <input id="nombreAsign_mostrar" type="text" nombre="nombreAsign_mostrar"/>
      </br>
      <label for="cursoAsign_mostrar">Curso: </label>
      <input id="cursoAsign_mostrar" type="text" nombre="cursoAsign_mostrar"/>
      </br>
      <input id="boton_mostrar" name="botonAsignatura" type="submit" value="Mostrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
</div>
<div class="texto_gestion">
  <textarea id="resultado" name="textoAsignatura" cols="150" rows="15"></textarea>
</div>
</BODY>
</HTML>