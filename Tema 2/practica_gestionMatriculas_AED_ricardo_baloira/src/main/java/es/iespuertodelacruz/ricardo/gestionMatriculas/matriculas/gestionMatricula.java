package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.AlumnoDAO;
import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.AsignaturaDAO;
import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.GestorConexionDDBB;
import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.MatriculaDAO;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Alumno;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Asignatura;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Matricula;

/**
 * Servlet implementation class gestionMatricula
 */
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		GestorConexionDDBB gc = (GestorConexionDDBB) request.getServletContext().getAttribute("gc");	
		MatriculaDAO matriculaDao = new MatriculaDAO(gc);
		
		String boton = request.getParameter("botonMatricula");
		String textoMatricula = (String) request.getSession().getAttribute("textoMatricula");
		if(textoMatricula == null) {
			textoMatricula = "";
		}
		
		if(boton.equalsIgnoreCase("Borrar")) {
			String idMat_borrar = request.getParameter("idMat_borrar");
			if(idMat_borrar!=null && !idMat_borrar.isEmpty()) {
				boolean resultado = matriculaDao.delete(idMat_borrar);
				if(resultado==true) {
					textoMatricula += "Borrado con exito";
					request.getSession().setAttribute("textoMatricula", textoMatricula);
				}
				if(resultado==false) {
					textoMatricula += "Error al borrar";
					request.getSession().setAttribute("textoMatricula", textoMatricula);
				}
			}
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			String anioMat_mostrar= request.getParameter("anioMat_mostrar");
			String dniMat_mostrar = request.getParameter("dniMat_mostrar");
			List<Matricula> encontrados;
			if((!anioMat_mostrar.isEmpty()) && (dniMat_mostrar.isEmpty())) {
				encontrados = matriculaDao.findByYear(anioMat_mostrar);
				for (Matricula m : encontrados) {
					ObjectMapper mapper = new ObjectMapper();
					String strMatAnio = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m);
					textoMatricula += strMatAnio;
					textoMatricula += "\n";
				}
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
			if((!dniMat_mostrar.isEmpty()) && (anioMat_mostrar.isEmpty())) {
				encontrados = matriculaDao.findByDni(dniMat_mostrar);
				for (Matricula m : encontrados) {
					ObjectMapper mapper = new ObjectMapper();
					String strMatDni = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(m);
					textoMatricula += strMatDni;
					textoMatricula += "\n";
				}
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			AlumnoDAO alumnoDao = new AlumnoDAO(gc);
			AsignaturaDAO asignaturaDao = new AsignaturaDAO(gc);
			String dniMat_agregar = request.getParameter("dniMat_agregar");
			String anioMat_agregar = request.getParameter("anioMat_agregar");
			String asignMat_agregar = request.getParameter("asignMat_agregar");
			
			if((dniMat_agregar!=null && !dniMat_agregar.isEmpty()) 
					&& (anioMat_agregar!=null && !anioMat_agregar.isEmpty())
					&& (asignMat_agregar!=null && !asignMat_agregar.isEmpty())) {
				Matricula agregado;
				Alumno alumno = alumnoDao.findById(dniMat_agregar);
				int anioMatricula = Integer.valueOf(anioMat_agregar); 
				List<String> strAsign = Arrays.asList(asignMat_agregar.split("\\s*,\\s*"));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				
				for (String str : strAsign) {
					asignaturas.add(asignaturaDao.findById(str));
				}
				
				agregado = matriculaDao.save(new Matricula(alumno, anioMatricula, asignaturas));
				ObjectMapper mapper = new ObjectMapper();
				String strMat = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(agregado);
				textoMatricula += strMat;
				request.getSession().setAttribute("textoMatricula", textoMatricula);
			}
		}
		if(boton.equalsIgnoreCase("Editar")) {
			AsignaturaDAO asignaturaDao = new AsignaturaDAO(gc);
			boolean resultado = false;
			String dniMat_editar = request.getParameter("dniMat_editar");
			String idMat_editar = request.getParameter("idMat_editar");
			String anioMat_editar = request.getParameter("anioMat_editar");
			String asignMat_editar = request.getParameter("asignMat_editar");
		
			if((dniMat_editar!=null && !dniMat_editar.isEmpty()) 
					&& (idMat_editar!=null && !idMat_editar.isEmpty())
					&& (anioMat_editar!=null && !anioMat_editar.isEmpty())
					&& (asignMat_editar!=null && !asignMat_editar.isEmpty())) {
				
				int idMatricula = Integer.valueOf(idMat_editar);
				int anioMatricula = Integer.valueOf(anioMat_editar);
				List<String> strAsign = Arrays.asList(asignMat_editar.split("\\s*,\\s*"));
				ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
				
				for (String str : strAsign) {
					asignaturas.add(asignaturaDao.findById(str));
				}
				
				resultado = matriculaDao.update(new Matricula(idMatricula, new Alumno(dniMat_editar), anioMatricula, 
						asignaturas));
				
				if(resultado==true) {
					textoMatricula += "Editado con exito";
					request.getSession().setAttribute("textoMatricula", textoMatricula);
				}
				if(resultado==false) {
					textoMatricula += "Error al editar";
					request.getSession().setAttribute("textoMatricula", textoMatricula);
				}
			}
		}
		textoMatricula += "\n";
		request.getSession().setAttribute("textoMatricula", textoMatricula);
		request.getRequestDispatcher("matriculas.jsp").forward(request, response);
	}

}