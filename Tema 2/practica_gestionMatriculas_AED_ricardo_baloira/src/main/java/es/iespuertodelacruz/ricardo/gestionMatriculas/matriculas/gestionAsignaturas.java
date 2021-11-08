package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.AsignaturaDAO;
import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.GestorConexionDDBB;
import es.iespuertodelacruz.ricardo.gestionMatriculas.modelo.Asignatura;

/**
 * Servlet implementation class gestionAsignaturas
 */
public class gestionAsignaturas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionAsignaturas() {
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
		AsignaturaDAO asignaturaDao = new AsignaturaDAO(gc);
		
		String boton = request.getParameter("botonAsignatura");
		String textoAsignatura = (String) request.getSession().getAttribute("textoAsignatura");
		
		
		if(boton.equalsIgnoreCase("Borrar")) {
			String idAsign_borrar = request.getParameter("idAsign_borrar");
			if(idAsign_borrar!=null && !idAsign_borrar.isEmpty()) {
				boolean resultado = asignaturaDao.delete(idAsign_borrar);
				if(resultado==true) {
					textoAsignatura += "Borrado con exito";
					request.getSession().setAttribute("textoAsignatura", textoAsignatura);
				}
			}
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			String idAsign_mostrar= request.getParameter("idAsign_mostrar");
			String cursoAsign_mostrar = request.getParameter("cursoAsign_mostrar");
			Asignatura encontrado;
			if((!cursoAsign_mostrar.isEmpty()) && (idAsign_mostrar.isEmpty())) {
				System.out.println("Encontrado por curso");
				encontrado = asignaturaDao.findByCurso(cursoAsign_mostrar);
				textoAsignatura += encontrado.toString();
				request.getSession().setAttribute("textoAsignatura", textoAsignatura);
			}
			if((!idAsign_mostrar.isEmpty()) && (cursoAsign_mostrar.isEmpty())) {
				System.out.println("Encontrado por id");
				encontrado = asignaturaDao.findById(idAsign_mostrar);
				textoAsignatura += encontrado.toString();
				request.getSession().setAttribute("textoAsignatura", textoAsignatura);
			}
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			String nombreAsign_agregar = request.getParameter("nombreAsign_agregar");
			String cursoAsign_agregar = request.getParameter("cursoAsign_agregar");
			
			if((cursoAsign_agregar!=null && !cursoAsign_agregar.isEmpty())
					&& (nombreAsign_agregar!=null && !nombreAsign_agregar.isEmpty())) {
				Asignatura agregado;
				agregado = asignaturaDao.save(new Asignatura(nombreAsign_agregar, cursoAsign_agregar));
				textoAsignatura += agregado.toString();
				request.getSession().setAttribute("textoAsignatura", textoAsignatura);
			}
		}
		if(boton.equalsIgnoreCase("Editar")) {
			String nombreAsign_editar = request.getParameter("nombreAsign_editar");
			String idAsign_editar = request.getParameter("idAsign_editar");
			String cursoAsign_editar = request.getParameter("cursoAsign_editar");

			
			if((idAsign_editar!=null && !idAsign_editar.isEmpty()) 
					&& (cursoAsign_editar!=null && !cursoAsign_editar.isEmpty())
					&& (nombreAsign_editar!=null && !nombreAsign_editar.isEmpty())) {
				int idAsignatura = Integer.valueOf(idAsign_editar);
				boolean resultado = asignaturaDao.update(new Asignatura(idAsignatura, nombreAsign_editar, cursoAsign_editar));
				if(resultado==true) {
					textoAsignatura += "Editado con exito";
					request.getSession().setAttribute("textoAsignatura", textoAsignatura);
				}
			}
		}
		textoAsignatura += "\n";
		request.getSession().setAttribute("textoAsignatura", textoAsignatura);
		request.getRequestDispatcher("asignaturas.jsp").forward(request, response);
	}

}
