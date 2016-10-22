package it.app.servlet;

import it.app.servlet.utils.ServletUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private Logger log = Logger.getLogger(getClass());
	private ServletUtils utils;
	
    public void init() throws ServletException {
    	   
    	utils = new ServletUtils();
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String strName = request.getParameter("fldName");
		String strCountry = request.getParameter("fldCountry");
		String strEmail = request.getParameter("fldEmail");
		String strPass = request.getParameter("fldPass");
		String strMsg = null;
		PreparedStatement pst = null;
		try {
		
			if(utils.checkIsEmpty(strName)){
				
				strMsg = "Name";
			}
			else if(utils.checkIsEmpty(strCountry)){
				
				strMsg = "Country";
			}
			else if(utils.checkIsEmpty(strCountry)){
				
				strMsg = "Email";
			}
			else if(utils.checkIsEmpty(strCountry)){

				strMsg = "Password";
			}

			PrintWriter out = response.getWriter();
			if(null != strMsg){
			
				strMsg += " can't be null or empty";
				utils.addPrintMessage(out, strMsg, true);

				RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
				rd.include(request, response);
			}
			else{
			
				Connection con = (Connection) getServletContext().getAttribute("DbConnection");
				pst = con.prepareStatement("insert into users (name, email, password, country)"
															 + "values (?, ?, ?, ?)");
				pst.setString(1, strName);
				pst.setString(2, strEmail);
				pst.setString(3, strPass);
				pst.setString(4, strCountry);
				
				pst.execute();
				
				log.info("User registered with email : " + strEmail);
				
				// forward to login page to login
				strMsg = "Registration successful, please login below";
				utils.addPrintMessage(out, strMsg, false);
				
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				rd.include(request, response);
			}
		}
		catch (SQLException e) {

			log.error("Database connection problem");
			e.printStackTrace();
			throw new ServletException("Db Connectoin problem");
		}
		finally{
			
			try {
				
				if(null != pst && !pst.isClosed()){
				
					pst.close();
				}
			} 
			catch (SQLException e) {
				
				log.error("SQLException in closing the Prepared Statement");
				e.printStackTrace();
			
			}
		}
	}
}
