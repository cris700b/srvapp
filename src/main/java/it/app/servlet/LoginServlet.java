package it.app.servlet;

import it.app.servlet.utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Login Servlet", 
			urlPatterns = {"/login"}, 
			initParams = {
					@WebInitParam(name = "appUser", value = "app_user"), 
					@WebInitParam(name = "appPass", value = "app_pass")
			})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String strUserId = "admin";
	private final String strPass = "pass";
	private ServletUtils utils;
    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
   
    	utils = new ServletUtils();
    	
//    	// we can create DBconnection resource here 
//    	// and set it to Servlet Context
//    	if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysqlDB")
//    		&& getServletContext().getInitParameter("dbUser").equals("mysqlUser")
//    		&& getServletContext().getInitParameter("dbUserPass").equals("mysqlPass")){
//    		
//    		getServletContext().setAttribute("DB_Success", "true");
//    	}
//    	else{
//    		
//    		throw new ServletException("DB Connection error");
//    	}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	response.sendRedirect("login.html");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get request params for userId an password
		String strFldUser= request.getParameter("fldUser");
		String strFldPass = request.getParameter("fldPass");
		
		// get servlet config init params
//		String strUserId = getServletConfig().getInitParameter("appUser");
//		String strPass = getServletConfig().getInitParameter("appPass");
		
		// logging example
		log("user = " + strFldUser + " :: pass = " + strFldPass);
		
		if(strUserId.equals(strFldUser) && strPass.equals(strFldPass)){
			
			// creating the user's http session
			utils.createHttpSession(request, "user", strUserId, 30);
			
			// creating the user session cookie
			utils.setLoginCookie(response, "user", strFldUser, 30);
			
			// get the encoded url string
			String strEncodedUrl = response.encodeRedirectURL("loginSuccess.jsp");
			response.sendRedirect(strEncodedUrl);
		}
		else{
			
			this.handleLoginError(request, response);
		}
	}

	/**
	 * handles the login error
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleLoginError(HttpServletRequest request, 
								  HttpServletResponse response) 
							throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		out.println("<font color=red>User name or password incorrect</font>");

		RequestDispatcher reqDispatcher = getServletContext().getRequestDispatcher("/login.html");
		reqDispatcher.include(request, response);
	}
}
