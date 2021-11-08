<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
  <title> alumnos.html </title>
  <meta name="author" content="Ricardo Baloira Armas"/>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <link rel="stylesheet" type="text/css" href="css/alumnos_css.css"/>
</HEAD>
<BODY>
<h1>Gestionar Instituto</h1>
<h3>Alumnos</h3>
<div class="formularios">
  <div class="agregar">
    <h4>Agregar alumno</h4>
    <form name="formulario_agregar" action="gestionAlumnos" method="post">
      <label for="nombre_agregar">*Nombre: </label>
      <input id="nombre_agregar" type="text" name="nombre_agregar"/>
      </br>
      <label for="apellidos_agregar">Apellidos: </label>
      <input id="apellidos_agregar" type="text" name="apellidos_agregar"/>
      </br>
      <label for="nac_agregar">Nacimiento: </label>
      <input id="nac_agregar" type="text" name="nac_agregar" placeholder="yyyy-MM-dd"/>
      </br>
      <label for="dni_agregar">*DNI: </label>
      <input id="dni_agregar" type="text" name="dni_agregar"/>
      </br>
      <input id="boton_agregar" name="botonAlumno" type="submit" value="Agregar" style="width:90%"/>
    </form>
  </div>

  <div class="borrar">
    <h4>Borrar alumno</h4>
    <form name="formulario_borrar" action="gestionAlumnos" method="post">
      <label for="dni_borrar">*DNI: </label>
      <input id="dni_borrar" type="text" name="dni_borrar"/>
      </br>
      <input id="boton_borrar" name="botonAlumno" type="submit" value="Borrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>

  <div class="editar">
    <h4>Editar alumno</h4>
    <form name="formulario_editar" action="gestionAlumnos" method="post">
      <label for="nombre_editar">*Nombre: </label>
      <input id="nombre_editar" type="text" name="nombre_editar"/>
      </br>
      <label for="apellidos_editar">Apellidos: </label>
      <input id="apellidos_editar" type="text" name="apellidos_editar"/>
      </br>
      <label for="nac_editar">Nacimiento: </label>
      <input id="nac_editar" type="text" name="nac_editar" placeholder="yyyy-MM-dd"/>
      </br>
      <label for="dni_editar">*DNI: </label>
      <input id="dni_editar" type="text" name="dni_editar"/>
      </br>
      <input id="boton_editar" name="botonAlumno" type="submit" value="Editar" style="width:90%"/>
    </form>
  </div>

  <div class="mostrar">
    <h4>Mostrar alumnos:</h4>
    <h5>(escribir en uno de los campos únicamente)</h5>
    <form name="formulario_mostrar" action="gestionAlumnos" method="post">
      <label for="nombre_mostrar">Nombre: </label>
      <input id="nombre_mostrar" type="text" name="nombre_mostrar"/>
      </br>
      <label for="dni_mostrar">DNI: </label>
      <input id="dni_mostrar" type="text" name="dni_mostrar"/>
      </br>
      <input id="boton_mostrar" name="botonAlumno" type="submit" value="Mostrar" style="width:90%"/>
      </br>
      </br>
      </br>
      </br>
    </form>
  </div>
  <spam class="nota"><strong>Nota:</strong> Los campos marcados con asterisco * son obligatorios</spam>
</div>
</div>
<div class="texto_gestion">
  <textarea id="resultado" cols="150" rows="15">${textoAlumno}</textarea>
</div>
</BODY>
</HTML>
