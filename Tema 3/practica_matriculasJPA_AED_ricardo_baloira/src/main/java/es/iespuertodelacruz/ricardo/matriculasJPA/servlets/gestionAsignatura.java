package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AsignaturaRepository;

/**
 * Servlet implementation class gestionAsignatura
 */
@WebServlet({ "/gestionAsignatura", "/gestionasignatura" })
public class gestionAsignatura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionAsignatura() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
		AsignaturaRepository asignaturaRepository = new AsignaturaRepository(emf);
		String idasignatura = request.getParameter("idasignatura");
		request.setAttribute("asignatura", asignaturaRepository.findById(idasignatura));
		response.sendRedirect("users/datosAsignatura.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
