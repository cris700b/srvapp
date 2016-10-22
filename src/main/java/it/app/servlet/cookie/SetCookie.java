package it.app.servlet.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SetCookie
 */
@WebServlet("/cookie/setCookie")
public class SetCookie extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static int count = 0;   
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetCookie() {

    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Cookie[] arrRequestCookies = request.getCookies();
		
		PrintWriter out = response.getWriter();
		out.write("<html><head></head><body>");
		out.write("<h3>Hello Browser !!! </h3>");
		if(null != arrRequestCookies){
			
			out.write("<h3>Request Cookies : </h3>");
			for(Cookie cookie : arrRequestCookies){
				
				out.write("Name = " + cookie.getName()
							+ ", Value = " + cookie.getValue()
							+ ", Comment = " + cookie.getComment()
							+ ", Domain = " + cookie.getDomain()
							+ ", MaxAge = " + cookie.getMaxAge()
							+ ", Path = " + cookie.getPath()
							+ ", Version = " + cookie.getVersion());
				out.write("<br />");
			}
		}
		
		// set cookies for counter, accessible to only this servlet
		count++;
		
		Cookie counterCookie = new Cookie("Counter", String.valueOf(count));
		
		// add some description to be viewed in browser cookie viewer
		counterCookie.setComment("SetCookie Counter");
		
		// setting max age to be 1 day
		counterCookie.setMaxAge(24 * 60 * 60);
		
		// set path to make it accessible to only this servlet
		counterCookie.setPath("/srvapp/cookie/setCookie");
		
		// adding cookie to the response
		response.addCookie(counterCookie);
		
		// set domain specific cookie
		Cookie domainCookie = new Cookie("DomainCookie", "DomainCookie value : " + String.valueOf(count));
		domainCookie.setComment("Domain Cookie Comment");
		response.addCookie(domainCookie);
		
		out.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
