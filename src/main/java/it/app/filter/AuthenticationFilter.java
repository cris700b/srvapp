package it.app.filter;

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
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/authenticationFilter")
public class AuthenticationFilter implements Filter {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig fCfg) throws ServletException {
		
		this.log.info("AuthenticationFilter initialized");
	}
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String strUri = req.getRequestURI();

		this.log.info("Requested resource :: " + strUri);
		
		// se la sessione e null, non sono authenticato
		// quando faccio il login, viene creata la sessione
		// quindi nelle chiamate successive avro sempre la sessione
		// finche non faccio logout
		// quindi le uniche risorse disponibili senza una sessione
		// sono la pagina di login ed loginServlet
		HttpSession session= req.getSession(false);
		if(null == session 
		    && !(strUri.endsWith("html") || strUri.endsWith("login") 
		    	 || strUri.endsWith("register"))){
			
			this.log.error("Unauthorized access request");
			res.sendRedirect("login.html");
		}
		else{
			
			// pass the request along the filter chain
			chain.doFilter(req, res);
		}
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}
}
