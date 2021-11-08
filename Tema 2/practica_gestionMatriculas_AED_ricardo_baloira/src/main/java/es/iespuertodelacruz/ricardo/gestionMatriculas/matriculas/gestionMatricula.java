package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String texto = (String) request.getSession().getAttribute("textoMatricula");
		
		if(boton.equalsIgnoreCase("Borrar")) {
			String idMat_borrar = request.getParameter("idMat_borrar");
			if(idMat_borrar!=null && !idMat_borrar.isEmpty()) {
				boolean resultado = matriculaDao.delete(idMat_borrar);
				if(resultado==true) {
					System.out.println("Borrado con exito");
				}
			}
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			String anioMat_mostrar= request.getParameter("anioMat_mostrar");
			String dniMat_mostrar = request.getParameter("dniMat_mostrar");
			Matricula encontrado;
			if((!anioMat_mostrar.isEmpty()) && (dniMat_mostrar.isEmpty())) {
				System.out.println("Encontrado por anio");
				encontrado = matriculaDao.findById(anioMat_mostrar);
				System.out.println(encontrado.toString());
			}
			if((!dniMat_mostrar.isEmpty()) && (anioMat_mostrar.isEmpty())) {
				System.out.println("Encontrado por dni");
				encontrado = matriculaDao.findById(dniMat_mostrar);
				System.out.println(encontrado.toString());
			}
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			String dniMat_agregar = request.getParameter("dniMat_agregar");
			String anioMat_agregar = request.getParameter("anioMat_agregar");
			String asignMat_agregar = request.getParameter("asignMat_agregar");
			
			if((dniMat_agregar!=null && !dniMat_agregar.isEmpty()) 
					&& (anioMat_agregar!=null && !anioMat_agregar.isEmpty())
					&& (asignMat_agregar!=null && !asignMat_agregar.isEmpty())) {
				Matricula agregado;
				int anioMatricula = Integer.valueOf(anioMat_agregar); 
				//agregado = matriculaDao.save(new Matricula(asignMat_agregar, new Alumno(dniMat_agregar), anioMatricula));
				//System.out.println(agregado.toString());
			}
		}
		if(boton.equalsIgnoreCase("Editar")) {
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
				//boolean resultado = matriculaDao.update(new Matricula(idAsignatura, nombreAsign_editar, cursoAsign_editar));
				//if(resultado==true) {
					//System.out.println("Editado con exito");
				//}
			}
		}
		request.getRequestDispatcher("matriculas.jsp").forward(request, response);
	}

}