package es.iespuertodelacruz.ricardo.acertarNumero.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.iespuertodelacruz.ricardo.acertarNumero.modelo.DatosApuesta;
import es.iespuertodelacruz.ricardo.acertarNumero.modelo.GestorFichero;

/**
 * Servlet implementation class Principal
 */
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	boolean acertado = false;
	boolean secretoExistente = false;
	GestorFichero gf;
  
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
		String pathToWeb = request.getServletContext().getRealPath(File.separator);
		gf = new GestorFichero(pathToWeb + "/WEB-INF/ganadores.txt");
		
		String nombre = (String) request.getSession().getAttribute("nombre");		
		request.setAttribute("nombre", nombre);
		nombre = request.getParameter("nombre");	
		request.getSession().setAttribute("nombre", nombre);
		
		if(nombre==null) {
			request.getRequestDispatcher("crearusuario.jsp").forward(request, response);
		}
		if(nombre!=null) {
			request.setAttribute("jugador", nombre);
			request.getRequestDispatcher("jugar.jsp").forward(request, response);	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		request.setCharacterEncoding("UTF-8");
		String nombre = (String) request.getSession().getAttribute("nombre");	
		ArrayList<DatosApuesta> apuestas = (ArrayList<DatosApuesta>) request.getSession().getAttribute("apuestas");
		request.getSession().setAttribute("apuestas", apuestas);
		Date horaSecreto = null;
		int compararSecreto = 0;
		String comparacion = null;
			
		if (acertado == true || secretoExistente == false) {
			apuestas = null;
			secretoExistente = true;
			int secreto = (int)(10000*Math.random());
			request.getServletContext().setAttribute("secreto", secreto);
			request.setAttribute("secreto", secreto);
			horaSecreto = new Date(System.currentTimeMillis());
			request.getServletContext().setAttribute("horaSecreto", horaSecreto);
			request.setAttribute("horaSecreto", horaSecreto);
				
			acertado = false;
			}
		
		if(apuestas == null) {
			apuestas = new ArrayList<DatosApuesta>();
			}
			
		request.setAttribute("jugador", nombre);
		request.setAttribute("horaSecreto", horaSecreto);
			
		Date hora = new Date(System.currentTimeMillis());
		    
		String strNumero = request.getParameter("apuesta");
		int numeroIntroducido = 0;
		
		try {
			numeroIntroducido = Integer.parseInt(strNumero);
		}catch(Exception ex) {}
		
		compararSecreto = (int) request.getServletContext().getAttribute("secreto");
		if(numeroIntroducido>compararSecreto) {
			comparacion = "Secreto <";
			}
		
		if(numeroIntroducido<compararSecreto) {
			comparacion = "Secreto >";
		    }
		if(numeroIntroducido==compararSecreto) {
			comparacion = "Acertaste! Secreto =";
			acertado = true;
		    	
		    String ganador = (String) request.getSession().getAttribute("nombre");
		    request.getServletContext().setAttribute("ganador", ganador);
		    Date horaGanado = hora;
		    request.getServletContext().setAttribute("horaGanado", horaGanado);
		    int secretoAcertado = numeroIntroducido;
		    request.getServletContext().setAttribute("secretoAcertado", secretoAcertado);	
		    	
		    request.setAttribute("ganador", ganador);
		    request.setAttribute("horaGanado", horaGanado);
		    request.setAttribute("secretoAcertado", secretoAcertado);
		    
		    gf.escribirFichero(ganador, secretoAcertado, horaGanado);
		    }
		
		DatosApuesta datos = new DatosApuesta(nombre, numeroIntroducido, hora, comparacion);
		apuestas.add(datos);		    
		//request.getServletContext().setAttribute("apuestas", apuestas);	 
		request.getSession().setAttribute("apuestas", apuestas);
		    	
		request.getRequestDispatcher("jugar.jsp").forward(request, response);
		}
	}
