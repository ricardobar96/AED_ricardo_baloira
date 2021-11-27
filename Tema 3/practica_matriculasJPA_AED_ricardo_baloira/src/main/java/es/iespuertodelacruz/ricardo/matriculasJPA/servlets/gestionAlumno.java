package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import java.math.BigInteger;
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

import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AlumnoRepository;

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
		Alumno alumno;
		String id = request.getParameter("id");
		alumno = alumnoRepository.findById(id);
		String fechaL = String.valueOf(alumno.getFechanacimiento());
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
		
		request.setAttribute("nombre", alumno.getNombre());
		request.setAttribute("apellidos", alumno.getApellidos());
		request.setAttribute("fechaNac", String.valueOf(fechaNac));
		request.setAttribute("dni", alumno.getDni());
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