package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AlumnoRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.MatriculaRepository;

/**
 * Servlet implementation class gestionAlumno
 */
@WebServlet({ "/gestionAlumno", "/gestionalumno" })
public class gestionAlumno extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionAlumno() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		AlumnoRepository alumnoRepository = new AlumnoRepository(emf);
		MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
		Alumno alumno;
		Matricula matricula;
		String id = request.getParameter("id");
		alumno = alumnoRepository.findById(id);

		request.getSession().setAttribute("dniCrear", id);
		
		String fechaL = String.valueOf(alumno.getFechanacimiento());
		Long convertirFecha = Long.parseLong(fechaL);
		Date fechaD = new Date(convertirFecha);
		
		request.setAttribute("nombre", alumno.getNombre());
		request.setAttribute("apellidos", alumno.getApellidos());
		request.setAttribute("fechaNac", fechaD);
		request.setAttribute("dni", alumno.getDni());
		
		List<Matricula> matriculas;
		matriculas = matriculaRepository.findByDni(alumno.getDni());
		
		request.setAttribute("matriculas", matriculas);
		
		request.getRequestDispatcher("users/datosAlumno.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
