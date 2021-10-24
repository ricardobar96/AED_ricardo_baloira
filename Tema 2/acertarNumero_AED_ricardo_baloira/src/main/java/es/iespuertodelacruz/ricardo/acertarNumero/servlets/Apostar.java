package es.iespuertodelacruz.ricardo.acertarNumero.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Apostar
 */
@WebServlet("/Apostar")
public class Apostar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apostar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		TreeMap<Integer, Date> datosSecreto = (TreeMap<Integer, Date>) request.getServletContext().getAttribute("datosSecreto");
		if(datosSecreto==null) {
			datosSecreto = new TreeMap<Integer, Date>();
			
			int numero = (int)(10000*Math.random());
			
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date hora = new Date(System.currentTimeMillis());
			formatter.format(hora);
			
			datosSecreto.put(numero, hora);
			request.getServletContext().setAttribute("datosSecreto", datosSecreto);
		} 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String nombre = (String) request.getSession().getAttribute("nombre");
		
        response.setContentType("text/html");  
        
        String strNumero = request.getParameter("apuesta");
        Integer numeroIntroducido = null;
		try {
			numeroIntroducido =Integer.parseInt(strNumero);
		}catch(Exception ex) {}
        
		TreeMap<Integer, Date> datosSecreto = (TreeMap<Integer, Date>) request.getServletContext().getAttribute("datosSecreto");
		
		String horaGenerada = "hora";
		request.setAttribute("horaGenerada", horaGenerada);
		request.setAttribute("numeroIntroducido", numeroIntroducido);
		request.setAttribute("jugador", "( " +nombre + " )");
	    request.getRequestDispatcher("jugar.jsp").forward(request, response);
	    
		/**
        try (PrintWriter out = response.getWriter()) {
            out.println("Hora del actual secreto: ");
            out.println("<br>");
            out.println("Numero introducido: " + numeroIntroducido);
        }       
		**/		
	}
}
