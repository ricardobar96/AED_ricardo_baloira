package es.iespuertodelacruz.ricardo.TCricardo.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.TCricardo.utils.GestorFicheros;

/**
 * Servlet implementation class Principal
 */
@WebServlet({ "/Principal", "/Inicio" })
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
		
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		GestorFicheros gf;
		
		String nombre = request.getParameter("nombre");
		String nombreCargar = request.getParameter("nombreCargar");
		String texto = request.getParameter("texto");
		request.getSession().setAttribute("texto", texto);
		
		if(nombre!=null && nombreCargar==null) {
			gf = new GestorFicheros(nombre);			
			gf.escribirFichero(texto);
			//String textoguardado = gf.leerFichero();
			request.getSession().setAttribute("guardado", "El fichero: " + nombre + " se ha guardado");
			request.getSession().setAttribute("nombre", nombre);		
			//request.getSession().setAttribute("texto", textoguardado);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
