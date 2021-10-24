package es.iespuertodelacruz.ricardo.acertarNumero.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Principal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String nombre = (String) request.getServletContext().getAttribute("nombre");
		if(nombre==null) {
			request.getRequestDispatcher("crearusuario.jsp").forward(request, response);
		}
		request.getSession().setAttribute("nombre", nombre);
		if(nombre!=null) {
			request.getRequestDispatcher("jugar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String nombre = request.getParameter("nombre");
		request.getSession().setAttribute("nombre", nombre);
		request.getServletContext().setAttribute("nombre", nombre);
		request.getRequestDispatcher("jugar.jsp").forward(request, response);
		
	}

}
