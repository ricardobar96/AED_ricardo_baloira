package es.iespuertodelacruz.ricardo.matriculasJPA.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class AutenticacionUsuarios
 */
@WebFilter({ "/AutenticacionUsuarios", "/users/*" })
public class AutenticacionUsuarios implements Filter {

    /**
     * Default constructor. 
     */
    public AutenticacionUsuarios() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		if(req.getSession().getAttribute("user") != null) {
			chain.doFilter(request, response);
		}
		else {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect("../login.jsp");
		}
		
		req.getSession().getAttribute("user");

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
