package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		String textoAlumno = (String) request.getSession().getAttribute("textoAlumno");
		if(textoAlumno == null) {
			textoAlumno = "";
		}
		
		if(boton.equalsIgnoreCase("Borrar")) {
			String dni_borrar = request.getParameter("dni_borrar");
			if(dni_borrar!=null && !dni_borrar.isEmpty()) {
				boolean resultado = alumnoDao.delete(dni_borrar);
				if(resultado==true) {
					textoAlumno += "Borrado con exito";
					request.getSession().setAttribute("textoAlumno", textoAlumno);
				}
				if(resultado==false) {
					textoAlumno += "Error al borrar";
					request.getSession().setAttribute("textoAlumno", textoAlumno);
				}
			}
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			String nombre_mostrar = request.getParameter("nombre_mostrar");
			String dni_mostrar = request.getParameter("dni_mostrar");
			List<Alumno> encontrados;
			Alumno encontrado;
			if((!dni_mostrar.isEmpty()) && (nombre_mostrar.isEmpty())) {
				encontrado = alumnoDao.findById(dni_mostrar);
				
				ObjectMapper mapper = new ObjectMapper();
				String strAluDni = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(encontrado);
				
				textoAlumno += strAluDni;
				request.getSession().setAttribute("textoAlumno", textoAlumno);
			}
			if((!nombre_mostrar.isEmpty()) && (dni_mostrar.isEmpty())) {
				encontrados = alumnoDao.findByName(nombre_mostrar);
				for (Alumno a : encontrados) {
					ObjectMapper mapper = new ObjectMapper();
					String strAluNombre = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(a);
					textoAlumno += strAluNombre;
					textoAlumno += "\n";
				}				
				request.getSession().setAttribute("textoAlumno", textoAlumno);
			}
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			String nombre_agregar = request.getParameter("nombre_agregar");
			String dni_agregar = request.getParameter("dni_agregar");
			String apellidos_agregar = request.getParameter("apellidos_agregar");
			String nac_agregar = request.getParameter("nac_agregar");

			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = formatter.parse(nac_agregar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date sqlDate = new Date(date.getTime());
			
			if((nombre_agregar!=null && !nombre_agregar.isEmpty()) && (dni_agregar!=null && !dni_agregar.isEmpty())) {
				Alumno agregado;
				agregado = alumnoDao.save(new Alumno(dni_agregar, nombre_agregar, apellidos_agregar, sqlDate));
				
				ObjectMapper mapper = new ObjectMapper();
				String strAlu = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agregado);
				
				textoAlumno += strAlu;
				request.getSession().setAttribute("textoAlumno", textoAlumno);
			}
		}
		if(boton.equalsIgnoreCase("Editar")) {
			String nombre_editar = request.getParameter("nombre_editar");
			String dni_editar = request.getParameter("dni_editar");
			String apellidos_editar = request.getParameter("apellidos_editar");
			String nac_editar = request.getParameter("nac_editar");
			
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = formatter.parse(nac_editar);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date sqlDate = new Date(date.getTime());
			
			if((nombre_editar!=null && !nombre_editar.isEmpty()) && (dni_editar!=null && !dni_editar.isEmpty())) {
				boolean resultado = alumnoDao.update(new Alumno(dni_editar, nombre_editar, apellidos_editar, sqlDate));
				if(resultado==true) {
					textoAlumno += "Editado con exito";
					request.getSession().setAttribute("textoAlumno", textoAlumno);
				}
				if(resultado==false) {
					textoAlumno += "Error al editar";
					request.getSession().setAttribute("textoAlumno", textoAlumno);
				}
			}
		}
		textoAlumno += "\n";
		request.getSession().setAttribute("textoAlumno", textoAlumno);
		request.getRequestDispatcher("alumnos.jsp").forward(request, response);
	}

}
