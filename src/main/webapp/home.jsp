
<%@page import="it.app.model.UserModel"%>
<%@ page language="java" 
		 contentType="text/html; charset=ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	
	<title>Home Page</title>
	
</head>
<body>
	
<%
	UserModel user = (UserModel) session.getAttribute("user");
%>	

	<h2>
		Hello <%=user.getName() %>! Your login whas successfull!
	</h2>
	<h3>
		Your Email is : <%=user.getEmail() %>
	</h3>
	<h3>
		Your Country is : <%=user.getCountry() %>
	</h3>
	
	<br />
	
	<form action="logout" method="post">
		
		<input type="submit" value="Logout" />
	</form>
</body>
</html>
