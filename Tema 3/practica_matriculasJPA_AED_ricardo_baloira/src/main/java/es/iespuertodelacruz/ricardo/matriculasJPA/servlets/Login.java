package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Matricula;
import es.iespuertodelacruz.ricardo.matriculasJPA.entities.Usuario;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AlumnoRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.AsignaturaRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.MatriculaRepository;
import es.iespuertodelacruz.ricardo.matriculasJPA.repositories.UsuarioRepository;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario user = (Usuario)session.getAttribute("user");
		String redirect = "login.jsp";
		
		if(user != null)
			redirect="users/inicio.jsp";
		else {
			String paramUser = request.getParameter("user");
			String paramPassword = request.getParameter("password");
			EntityManagerFactory emf =(EntityManagerFactory)request.getServletContext().getAttribute("emf");
			UsuarioRepository usuarioR = new UsuarioRepository(emf);
			
			Usuario usuario = usuarioR.findByName(paramUser);
			
			if(usuario != null) {
				
				boolean okLogin = BCrypt.checkpw(paramPassword,usuario.getPassword());

				if( okLogin) {
					request.getSession().setAttribute("user", usuario);
					
					AlumnoRepository alumnoRepository = new AlumnoRepository(emf);
					List<Alumno> findAllAlumnos = alumnoRepository.findAll();
					request.getSession().setAttribute("listaAlumnos", findAllAlumnos);
					
					AsignaturaRepository asignaturaRepository = new AsignaturaRepository(emf);
					List<Asignatura> findAllAsignaturas = asignaturaRepository.findAll();
					request.getSession().setAttribute("listaAsignaturas", findAllAsignaturas);
					
					MatriculaRepository matriculaRepository = new MatriculaRepository(emf);
					List<Matricula> findAllMatriculas = matriculaRepository.findAll();
					request.getSession().setAttribute("listaMatriculas", findAllMatriculas);
					
					redirect="users/inicio.jsp";
				}
				
			}
		}
		response.sendRedirect(redirect);
	}

}

