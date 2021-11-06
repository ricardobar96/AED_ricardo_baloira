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
    <form name="formulario_agregar" action="enviarinfo" method="post">
      <label for="id_agregar">*ID asignatura: </label>
      <input id="id_agregar" type="text"/>
      </br>
      <label for="matricula_agregar">*ID matrícula: </label>
      <input id="matricula_agregar" type="text"/>
      </br>
      <label for="nombreAsign_agregar">*Nombre: </label>
      <input id="nombreAsign_agregar" type="text"/>
      </br>
      <label for="curso_agregar">*Curso: </label>
      <input id="curso_agregar" type="text"/>
      </br>
      <input id="boton_agregar" type="submit" value="Agregar" style="width:90%"/>
    </form>
  </div>

  <div class="borrar">
    <h4>Borrar asignatura</h4>
    <form name="formulario_borrar" action="enviarinfo" method="post">
      <label for="id_borrar">*ID asignatura: </label>
      <input id="id_borrar" type="text"/>
      </br>
      <input id="boton_borrar" type="submit" value="Borrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>

  <div class="editar">
    <h4>Editar asignatura</h4>
    <form name="formulario_editar" action="enviarinfo" method="post">
      <label for="id_editar">*ID asignatura: </label>
      <input id="nombre_editar" type="text"/>
      </br>
      <label for="nombreAsign_editar">*Nombre: </label>
      <input id="nombreAsign_editar" type="text"/>
      </br>
      <label for="curso_editar">*Curso: </label>
      <input id="curso_editar" type="text"/>
      </br>
      </br>
      </br>
      <input id="boton_editar" type="submit" value="Editar" style="width:90%"/>
    </form>
  </div>

  <div class="mostrar">
    <h4>Mostrar asignaturas:</h4>
    <h5>(escribir en uno de los campos únicamente)</h5>
    <form name="formulario_mostrar" action="enviarinfo" method="post">
      <label for="curso_mostrar">Curso: </label>
      <input id="curso_mostrar" type="text"/>
      </br>
      <label for="matricula_mostrar">ID matrícula: </label>
      <input id="matricula_mostrar" type="text"/>
      </br>
      <input id="boton_mostrar" type="submit" value="Mostrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
</div>
<div class="texto_gestion">
  <textarea id="resultado" cols="150" rows="15"></textarea>
</div>
</BODY>
</HTML>