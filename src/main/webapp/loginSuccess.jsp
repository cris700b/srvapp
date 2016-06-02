
<%@ page language="java" 
		 contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>Login Success Page</title>
	
</head>
<body>
	
<%
	// allow access only if session exists
	String strUser = null;
	if(null == session.getAttribute("user")){
		
		response.sendRedirect("login.html");
	}
	else{
		
		strUser = (String) session.getAttribute("user");
	}

	String strUsername = null;
	String strSessionId = null;
	Cookie[] arrCookies = request.getCookies();
	if(null != arrCookies){
		
		for(Cookie cookie : arrCookies){
			
			if(cookie.getName().equals("user")){
				
				strUsername = cookie.getValue();
			}
			
			if(cookie.getName().equals("JSESSIONID")){
				
				strSessionId = cookie.getValue();
			}
		}
	}
	else{
		
		strSessionId = session.getId();
	}
%>	

	<h2>
		Hello <%=strUsername %>! Your login whas successfull!
	</h2>
	<h3>
		Your Session ID is <%=strSessionId %>
	</h3>
	
	User : <%=strUser %>
	
	<br />
	
	<a href="checkoutPage.jsp">Checkout Page</a>
	<form action="logout" method="post">
		
		<input type="submit" value="Logout" />
	</form>
</body>
</html>
