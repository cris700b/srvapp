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
 * Servlet implementation class GetCookie
 */
@WebServlet("/cookie/getCookie")
public class GetCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCookie() {
        super();
        // TODO Auto-generated constructor stub
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
				
				// delete cookie
				if(cookie.getName().equals("DomainCookie")){
					
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		
		out.write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
