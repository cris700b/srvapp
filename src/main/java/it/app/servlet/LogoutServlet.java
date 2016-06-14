package it.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private Logger log = Logger.getLogger(this.getClass());
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {

    	super();
    }

    @Override
    protected void doGet(HttpServletRequest request, 
    					 HttpServletResponse response)
    				throws ServletException, IOException {
    	
    	response.sendRedirect("login.html");
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		Cookie[] arrCookies = request.getCookies();
		if(null != arrCookies){
			
			for(Cookie cookie : arrCookies){
				
				if(cookie.getName().equals("JSESSIONID")){

					log.info("JSESSIONID = " + cookie.getValue());
					break;
				}
			}
		}
		
		// invalidate the session if it exists
		HttpSession session = request.getSession(false);
		this.log.info("User = " + session.getAttribute("user"));
		
		if(null != session){
			
			session.invalidate();
			this.log.info("Session invalidated");
		}
		
		// there is no encoding because we have invalidated the session
		response.sendRedirect("login.html");
	}
}
