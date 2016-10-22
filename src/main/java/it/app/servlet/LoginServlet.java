package it.app.servlet;



import it.app.model.UserModel;
import it.app.servlet.utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Login Servlet", 
			urlPatterns = {"/login"}) 
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger log = Logger.getLogger(this.getClass());
	private ServletUtils utils;
    
	/**
     * Default constructor. 
     */
    public LoginServlet() {
    }

    @Override
    public void init() throws ServletException {
   
    	utils = new ServletUtils();
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
	
		String strErrMsg = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
		
			// get request params for userId an password
			String strFldEmail= request.getParameter("fldEmail");
			String strFldPass = request.getParameter("fldPass");
			
			if(null == strFldEmail || strFldEmail.trim().length() == 0){
				
				strErrMsg = "User Email ";
			}
			else if(null == strFldPass || strFldPass.trim().length() == 0){
				
				strErrMsg = "Password ";
			}
			
			PrintWriter out = response.getWriter();
			if(strErrMsg.length() > 0){
				
				strErrMsg += "can't be null or empty";

				this.utils.addPrintMessage(out, strErrMsg, true);
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("login.html");
				dispatcher.include(request, response);
			}
			else{
				
				Connection con = (Connection) getServletContext().getAttribute("DbConnection");
				pst = con.prepareStatement("select id, name, email, country from users"
															  + " where email = ?"
															  + " and password = ?"
															  + " limit 1");
				pst.setString(1, strFldEmail);
				pst.setString(2, strFldPass);
				rs = pst.executeQuery();
				if(null != rs && rs.next()){
					
					UserModel userModel = new UserModel(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("country"));

					this.log.info("User found with details : " + userModel);
					
					// for creating the session i could have used the utils.createSession() method
					HttpSession session = request.getSession();
					session.setAttribute("user", userModel);
					response.sendRedirect(response.encodeRedirectURL("home.jsp"));
				}
				else{
					
					this.log.error("No User not found with email : " + strFldEmail);
					this.utils.addPrintMessage(out, "No user found with the given email", true);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.html");
					dispatcher.include(request, response);
				}
			}
		} 
		catch (SQLException e) {

			e.printStackTrace();
			
			String strTmpMsg = "Database connection problem";
			this.log.error(strTmpMsg);
			throw new ServletException(strErrMsg);
		}
		finally{
			
			try {
				
				rs.close();
				pst.close();
			} 
			catch (SQLException e) {

				this.log.error("SQLException in closing PreparedStatement or ResultSet");
			}
		}
	}
}
