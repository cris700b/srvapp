package it.app.servlet.errorHandler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AppErrorHandler
 */
@WebServlet("/appErrorHandler")
public class AppErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processError(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processError(request, response);
	}


	private void processError(HttpServletRequest request,
								HttpServletResponse response) throws IOException {

		// analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String strServletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
		String strRequestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if(null == strServletName){
			
			strServletName = "Unknown";
		}
		
		if(null == strRequestUri){
			
			strRequestUri = "Unknown";
		}
		
		// set response content type
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.write("<html><head><title>");
		out.write("Exception/Error Details");
		out.write("</title></head><body>");
		if(500 != statusCode){
			
			out.write("<h3>Error Details</h3>");
			out.write("<strong>Status Code</strong> : ");
			out.write(statusCode);
			out.write("<br />");
			out.write("<strong>Request URI</strong> : ");
			out.write(strRequestUri);
		}
		else{
			
			out.write("<h3>Exception Details</h3>");
			out.write("<ul><li>Servlet Name : ");
			out.write(strServletName);
			out.write("</li>");
			out.write("<li>Exception Name : ");
			out.write(throwable.getClass().getName());
			out.write("</li>");
            out.write("<li>Requested URI : ");
            out.write(strRequestUri);
            out.write("</li>");
            out.write("<li>Exception Message : ");
            out.write(throwable.getMessage());
            out.write("</li></ul>");
		}
		
		out.write("<br /><br />");
        out.write("<a href=\"login.html\">Login Page</a>");
        out.write("</body></html>");
	}
}
