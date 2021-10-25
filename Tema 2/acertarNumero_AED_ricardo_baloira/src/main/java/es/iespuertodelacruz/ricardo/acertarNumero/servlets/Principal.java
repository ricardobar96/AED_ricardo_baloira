package es.iespuertodelacruz.ricardo.acertarNumero.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.acertarNumero.modelo.DatosApuesta;

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
		if(nombre!=null) {
			ArrayList<DatosApuesta> apuestas = (ArrayList<DatosApuesta>) request.getServletContext().getAttribute("apuestas");
			Date horaSecreto = null;
			int compararSecreto = 0;
			String comparacion = null;
			
			if (apuestas == null) {
				apuestas = new ArrayList<DatosApuesta>();
				int secreto = (int)(10000*Math.random());
				request.getServletContext().setAttribute("secreto", secreto);
				request.setAttribute("secreto", secreto);
				
				horaSecreto = new Date(System.currentTimeMillis());
				request.getServletContext().setAttribute("horaSecreto", horaSecreto);
				request.setAttribute("horaSecreto", horaSecreto);
			}
			request.setAttribute("jugador", nombre);
			request.setAttribute("horaSecreto", horaSecreto);
			
			Date hora = new Date(System.currentTimeMillis());
		    
		    String strNumero = request.getParameter("apuesta");
		    Integer numeroIntroducido = null;
			try {
				numeroIntroducido =Integer.parseInt(strNumero);
			}catch(Exception ex) {}
		    
			compararSecreto = (int) request.getServletContext().getAttribute("secreto");
		    if(numeroIntroducido>compararSecreto) {
		    	comparacion = "<";
		    }
		    if(numeroIntroducido<compararSecreto) {
		    	comparacion = ">";
		    }
		    if(numeroIntroducido==compararSecreto) {
		    	comparacion = "==";
		    }
		    
		    DatosApuesta datos = new DatosApuesta(nombre, numeroIntroducido, hora, comparacion);
		    apuestas.add(datos);
		    
		    request.getServletContext().setAttribute("apuestas", apuestas);
		    
		    
		    	
		    request.getRequestDispatcher("jugar.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String nombre = request.getParameter("nombre");
		request.getSession().setAttribute("nombre", nombre);
		request.getServletContext().setAttribute("nombre", nombre);
		
		request.setAttribute("jugador", nombre);
		request.getRequestDispatcher("jugar.jsp").forward(request, response);

	}
	
}
