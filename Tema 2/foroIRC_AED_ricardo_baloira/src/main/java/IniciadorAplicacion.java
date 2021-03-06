

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import es.iespuertodelacruz.ricardo.Foro.utils.GestorFichero;

/**
 * Application Lifecycle Listener implementation class IniciadorAplicacion
 *
 */
public class IniciadorAplicacion implements ServletContextListener {
	//GestorFichero gf = new GestorFichero("C:\\MIS DATOS\\DOCUMENTOS\\Eclipse Proyectos\\foroIRC\\src\\main\\webapp\\WEB-INF\\foro.txt");
	
    /**
     * Default constructor. 
     */
    public IniciadorAplicacion() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 	
    	String pathToWeb = sce.getServletContext().getRealPath(File.separator);
    	GestorFichero gf = new GestorFichero(pathToWeb + "/WEB-INF/foro.txt");
    	
    	sce.getServletContext().getAttribute("mensajeforo"); 	
    	try {
			gf.escribirFichero((ArrayList<String>) sce.getServletContext().getAttribute("mensajeforo"));
		} 
    	catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	String pathToWeb = sce.getServletContext().getRealPath(File.separator);
    	GestorFichero gf = new GestorFichero(pathToWeb + "/WEB-INF/foro.txt");
    	
    	if(gf.getFile().exists()) {
    		ArrayList<String> mensajeforo = new ArrayList<String>();
        	try {
    			mensajeforo = gf.leerFichero();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	sce.getServletContext().setAttribute("mensajeforo", mensajeforo);
    	}  
    }
	
}
