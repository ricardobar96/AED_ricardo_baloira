package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;
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
		String idmatricula = request.getParameter("idmatricula");
		matricula = matriculaRepository.findById(idmatricula);
		
		String fechaL = String.valueOf(matricula.getAlumno().getFechanacimiento());
		Date fechaNac = null;
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		
		try {
			date = formatter.parse(fechaL);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date != null) {
			fechaNac = new Date(date.getTime());
		}
		
		request.setAttribute("year", matricula.getYear());
		request.setAttribute("alumnoMat", " || Nombre: " + matricula.getAlumno().getNombre() + " " 
		+ matricula.getAlumno().getApellidos() + " || DNI: " + matricula.getAlumno().getDni() 
		+ " || Fecha Nacimiento: " + String.valueOf(fechaNac));
		request.getRequestDispatcher("users/datosMatricula.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
