package it.app.servlet.utils;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtils {

	/**
	 * creates the login cookie and adds it in the response object
	 * 
	 * @param response
	 * @param strCookieName
	 * @param strCookieValue
	 * @param intCookieAge - in minutes
	 */
	public void setLoginCookie(HttpServletResponse response, 
								 String strCookieName, 
								 String strCookieValue, 
								 int intCookieAge) {

		Cookie loginCookie = new Cookie(strCookieName, strCookieValue);
		
		// session expiration time
		loginCookie.setMaxAge(intCookieAge * 60);
		response.addCookie(loginCookie);
	}

	/**
	 * creates a new http session 
	 * and sets the attribute with the specified values
	 * 
	 * @param request
	 * @param strAttributeName
	 * @param strAttributeValue
	 * @param intSessionAge - in minutes
	 */
	public void createHttpSession(HttpServletRequest request, 
								  String strAttributeName,
								  Object objAttributeValue, 
								  int intSessionAge) {

		HttpSession session = request.getSession();
		session.setAttribute(strAttributeName, objAttributeValue);
		
		// setting the session expiration time
		session.setMaxInactiveInterval(intSessionAge * 60);
	}
	
	/**
	 * utility method for printing messages to the response page
	 * 
	 * @param out
	 * @param strMsg
	 * @param isError
	 */
	public void addPrintMessage(PrintWriter out, String strMsg, boolean isError){
		
		if(isError){

			out.print("<font color=red>");
		}
		else{
			
			out.print("<font color=green>");
		}

		out.print(strMsg);
		out.print("</font>");
	}
	
	/**
	 * checks if the passed string valiue is null or with length 0
	 * 
	 * @param strValue
	 * @return
	 */
	public boolean checkIsEmpty(String strValue) {
		
		boolean isEmpty = false;
		if(null == strValue || strValue.trim().length() == 0){
			
			isEmpty = true;
		}
		
		return isEmpty;
	}
}
