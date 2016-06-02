
<%@ page language="java" 
		 contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>Login Success Page</title>
	
</head>
<body>
	
<%
	String strUsername = null;

	// allow access only if session exists
	if(null == session.getAttribute("user")){
		
		response.sendRedirect("login.html");
	}
	else{
		
		strUsername = (String) session.getAttribute("user");	
	}
	
	String strSessionId = null;
	Cookie[] arrCookies = request.getCookies();
	if(null != arrCookies){
		
		for(Cookie cookie : arrCookies){
			
			if(cookie.getName().equals("user")){
				
				strUsername = cookie.getValue();
			}
		}
	}

	if(null == strUsername){
		
		response.sendRedirect("login.html");
	}
%>	

	<h2>Hello <%=strUsername %>, do the checkout</h2>
	<br />
	
	<form action="<%=response.encodeURL("logout") %>" method="post">
		
		<input type="submit" value="Logout" />
	</form>
</body>
</html>
