package it.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
//		Cookie loginCookie = null;
		Cookie[] arrCookies = request.getCookies();
		if(null != arrCookies){
			
			for(Cookie cookie : arrCookies){
				
//				if(cookie.getName().equals("user")){
				if(cookie.getName().equals("JSESSIONID")){

					log("JSESSIONID = " + cookie.getValue());
				}
				
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		
//		if(null != loginCookie){
//			
//			loginCookie.setMaxAge(0);
//			response.addCookie(loginCookie);
//		}
		
		// invalidate the session if it exists
		HttpSession session = request.getSession(false);
		log("User = " + session.getAttribute("user"));
		
		if(null != session){
			
			session.invalidate();
		}
		
		// there is no encoding because we have invalidated the session
		response.sendRedirect("login.html");
	}
}
