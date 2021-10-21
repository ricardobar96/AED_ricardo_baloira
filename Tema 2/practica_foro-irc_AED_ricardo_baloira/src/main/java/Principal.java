
import java.io.IOException;
import java.util.ArrayList;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setIntHeader("Refresh", 10);
		ArrayList<String> mensajeforo = (ArrayList<String>) request.getServletContext().getAttribute("mensajeforo");
		if (mensajeforo == null) {
			mensajeforo = new ArrayList<String>();
			request.getServletContext().setAttribute("mensajeforo", mensajeforo);
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String mensaje = request.getParameter("mensaje");
		if (nombre == null) {
			nombre = (String) request.getSession().getAttribute("nombre");
		}
		ArrayList<String> mensajeforo = (ArrayList<String>) request.getServletContext().getAttribute("mensajeforo");
		if (nombre.length() > 0 && mensaje.length() > 0) {
			mensajeforo.add(nombre + ": " + mensaje);
			request.getSession().setAttribute("nombre", nombre);
		}
		request.getServletContext().setAttribute("mensajeforo", mensajeforo);

		response.sendRedirect("index.jsp");
	}

}
