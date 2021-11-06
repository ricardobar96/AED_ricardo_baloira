package es.iespuertodelacruz.ricardo.gestionMatriculas.matriculas;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import es.iespuertodelacruz.ricardo.gestionMatriculas.dao.GestorConexionDDBB;

/**
 * Application Lifecycle Listener implementation class arrancarPool
 *
 */
public class arrancarPool implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public arrancarPool() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	GestorConexionDDBB gc = new GestorConexionDDBB("instituto", "root", "");
        sce.getServletContext().setAttribute("gc", gc);
    }
	
}
