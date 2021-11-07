package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.AlumnoDAO;
import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.GestorConexionDDBB;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;

/**
 * Servlet implementation class gestionAlumnos
 */
public class gestionAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionAlumnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		GestorConexionDDBB gc = (GestorConexionDDBB) request.getServletContext().getAttribute("gc");	
		AlumnoDAO alumnoDao = new AlumnoDAO(gc);
		
		String boton = request.getParameter("botonAlumno");
		String texto = (String) request.getSession().getAttribute("textoAlumno");
		
		
		if(boton.equalsIgnoreCase("Borrar")) {
			String dni_borrar = request.getParameter("dni_borrar");
			if(dni_borrar!=null && !dni_borrar.isEmpty()) {
				boolean resultado = alumnoDao.delete(dni_borrar);
				if(resultado==true) {
					System.out.println("Borrado con exito");
				}
			}
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			String nombre_mostrar = request.getParameter("nombre_mostrar");
			String dni_mostrar = request.getParameter("dni_mostrar");
			Alumno encontrado;
			if((!dni_mostrar.isEmpty()) && (nombre_mostrar.isEmpty())) {
				System.out.println("Encontrado por dni");
				encontrado = alumnoDao.findById(dni_mostrar);
				System.out.println(encontrado.toString());
			}
			if((!nombre_mostrar.isEmpty()) && (dni_mostrar.isEmpty())) {
				System.out.println("Encontrado por nombre");
				encontrado = alumnoDao.findById(nombre_mostrar);
				System.out.println(encontrado.toString());
			}
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			String nombre_agregar = request.getParameter("nombre_agregar");
			String dni_agregar = request.getParameter("dni_agregar");
			String apellidos_agregar = request.getParameter("apellidos_agregar");
			String nac_agregar = request.getParameter("nac_agregar");

			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date date = null;
			try {
				date = formatter.parse(nac_agregar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			if((nombre_agregar!=null && !nombre_agregar.isEmpty()) && (dni_agregar!=null && !dni_agregar.isEmpty())) {
				Alumno agregado;
				agregado = alumnoDao.save(new Alumno(dni_agregar, nombre_agregar, apellidos_agregar, (java.sql.Date) sqlDate));
				System.out.println(agregado.toString());
			}
		}
		if(boton.equalsIgnoreCase("Editar")) {
			String nombre_editar = request.getParameter("nombre_editar");
			String dni_editar = request.getParameter("dni_editar");
			String apellidos_editar = request.getParameter("apellidos_editar");
			String nac_editar = request.getParameter("nac_editar");
			
			DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			java.util.Date date = null;
			try {
				date = formatter.parse(nac_editar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			
			if((nombre_editar!=null && !nombre_editar.isEmpty()) && (dni_editar!=null && !dni_editar.isEmpty())) {
				boolean resultado = alumnoDao.update(new Alumno(dni_editar, nombre_editar, apellidos_editar, (java.sql.Date) sqlDate));
				if(resultado==true) {
					System.out.println("Editado con exito");
				}
			}
		}
		request.getRequestDispatcher("alumnos.jsp").forward(request, response);
	}

}
