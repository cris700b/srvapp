package it.app.servlet.utils;

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
								  String strAttributeValue, 
								  int intSessionAge) {

		HttpSession session = request.getSession();
		session.setAttribute(strAttributeName, strAttributeValue);
		
		// setting the session expiration time
		session.setMaxInactiveInterval(intSessionAge * 60);
	}
	
	
}
