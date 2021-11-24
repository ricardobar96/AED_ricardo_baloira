package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String dni = request.getParameter("dni");
		request.setAttribute("alumno", alumnoRepository.findById(dni));
		response.sendRedirect("users/datosAlumno.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
