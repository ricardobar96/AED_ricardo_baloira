

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> mensajeforo = (ArrayList<String>) request.getServletContext().getAttribute("mensajeforo");
		if(mensajeforo==null) {
			mensajeforo = new ArrayList<String>();
			request.getServletContext().setAttribute("mensajeforo", mensajeforo);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String mensaje = request.getParameter("mensaje");
		
		ArrayList<String> mensajeforo = (ArrayList<String>) request.getServletContext().getAttribute("mensajeforo");
		mensajeforo.add(nombre + ": " + mensaje);
		request.getServletContext().setAttribute("mensajeforo", mensajeforo);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
