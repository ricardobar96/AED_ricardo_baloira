package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AlumnoRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AsignaturaRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.MatriculaRepository;

/**
 * Servlet implementation class gestionMatricula
 */
@WebServlet({ "/gestionMatricula", "/gestionmatricula" })
public class gestionMatricula extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionMatricula() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");		
		MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
		Matricula matricula;
		Asignatura asignatura;
		AsignaturaRepository asignaturaRepository = new AsignaturaRepository(emf);
		String idmatricula = request.getParameter("idmatricula");
		request.getSession().setAttribute("idEditar", idmatricula);
		matricula = matriculaRepository.findById(idmatricula);
		
		String fechaL = String.valueOf(matricula.getAlumno().getFechanacimiento());
		Long convertirFecha = Long.parseLong(fechaL);
		Date fechaD = new Date(convertirFecha);
		
		request.setAttribute("year", matricula.getYear());
		request.setAttribute("alumnoMat", " || Nombre: " + matricula.getAlumno().getNombre() + " " 
		+ matricula.getAlumno().getApellidos() + " || DNI: " + matricula.getAlumno().getDni() 
		+ " || Fecha Nacimiento: " + fechaD);
		
		List<Asignatura> asignaturas;
		asignaturas = matricula.getAsignaturas();
		
		request.setAttribute("asignaturas", asignaturas);
		
		request.getRequestDispatcher("users/datosMatricula.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		AsignaturaRepository asignaturaRepository = new AsignaturaRepository(emf);
		AlumnoRepository alumnoRepository = new AlumnoRepository(emf);
		MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
		
		String boton = request.getParameter("botonMatricula");
		String textoMatricula = (String) request.getSession().getAttribute("textoMatricula");
		if(textoMatricula == null) {
			textoMatricula = "";
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			String dniMat_agregar = (String)request.getSession().getAttribute("dniCrear");
			String anioMat_agregar = request.getParameter("anioMat_agregar");
			String asignMat_agregar = request.getParameter("asignMat_agregar");
			
			if((anioMat_agregar!=null && !anioMat_agregar.isEmpty())
					&& (asignMat_agregar!=null && !asignMat_agregar.isEmpty())) {
				Matricula agregado;
				Matricula resultado;
				Alumno alumno = alumnoRepository.findById(dniMat_agregar);
				
				int anioMatricula = Integer.valueOf(anioMat_agregar); 
				List<String> strAsign = Arrays.asList(asignMat_agregar.split("\\s*,\\s*"));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				
				for (String str : strAsign) {
					asignaturas.add(asignaturaRepository.findById(str));
				}
				
				agregado = new Matricula(anioMatricula, alumno, asignaturas);
				resultado = matriculaRepository.save(agregado);
				
				textoMatricula += "Matricula creada con exito";
				textoMatricula += "\n";
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
			
			else {
				textoMatricula += "Fallo al crear matricula, rellene todos los campos por favor";
				textoMatricula += "\n";
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
			response.sendRedirect("users/crearMatricula.jsp");
		}
		if(boton.equalsIgnoreCase("Editar")) {
			Matricula resultado = null;
			String dniMat_editar = (String)request.getSession().getAttribute("dniCrear");
			String idMat_editar = (String)request.getSession().getAttribute("idEditar");
			String anioMat_editar = request.getParameter("anioMat_editar");
			String asignMat_editar = request.getParameter("asignMat_editar");
			
			if((anioMat_editar!=null && !anioMat_editar.isEmpty())
					&& (asignMat_editar!=null && !asignMat_editar.isEmpty())) {
				
				int idMatricula = Integer.valueOf(idMat_editar);
				int anioMatricula = Integer.valueOf(anioMat_editar);
				List<String> strAsign = Arrays.asList(asignMat_editar.split("\\s*,\\s*"));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				Matricula editar;
				
				for (String str : strAsign) {
					asignaturas.add(asignaturaRepository.findById(str));
				}
				
				Alumno alumno = alumnoRepository.findById(dniMat_editar);
				editar = new Matricula(idMatricula, anioMatricula, alumno, asignaturas);
				resultado = matriculaRepository.update(editar);
				textoMatricula += "Editado con exito";
				textoMatricula += "\n";
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
			
			else {
				textoMatricula += "Error al editar matricula, rellene todos los campos por favor";
				textoMatricula += "\n";
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
			response.sendRedirect("users/editarMatricula.jsp");
		}
		
		if(boton.equalsIgnoreCase("Borrar matricula")) {
			String idMat_borrar = (String)request.getSession().getAttribute("idEditar");
			matriculaRepository.delete(idMat_borrar);
			response.sendRedirect("users/alumnos.jsp");
		}
	}

}
