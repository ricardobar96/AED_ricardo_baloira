package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class gestionAlumnos
 */
public class gestionAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionAlumnos() {
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
		// TODO Auto-generated method stub
		String boton = request.getParameter("botonAlumno");
		String texto = (String) request.getSession().getAttribute("textoAlumno");
		if(boton.equalsIgnoreCase("Borrar")) {
			request.getSession().setAttribute("texto", "Borrar");
		}
		if(boton.equalsIgnoreCase("Mostrar")) {
			request.getSession().setAttribute("texto", "Mostrar");
		}
		if(boton.equalsIgnoreCase("Agregar")) {
			request.getSession().setAttribute("texto", "Agregar");
		}
		if(boton.equalsIgnoreCase("Editar")) {
			request.getSession().setAttribute("texto", "Editar");
		}
		request.getRequestDispatcher("alumnos.jsp").forward(request, response);
	}

}
