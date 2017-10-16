<%@ page import="com.rental.models.ErrorBean"%>
<%@ page import="com.rental.models.User"%>
<%
	User user = (User) session.getAttribute("user");
	if (user == null) {
		user = new User();
	}
	if (!"Admin".equalsIgnoreCase(user.getType())) {
		response.sendRedirect("../noccsess.jsp");
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/adminCss/adminNav.css">
<link rel="stylesheet" href="../css/customerCss/customer.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-default --primary">
				<div class="container">
					<div class="navbar--brand brand">
						<a href="#" class="brand__logo">
							<img src="../pictures/farmBureau.png" alt="Render logo">
						</a>
					</div>
					<ul class="nav">
							<li><a href="./adminHome.jsp"><button class="btn btn-outline-success" type="button">Home</button></a></li>
							<li><a href="./carrentaladmin.jsp"><button class="btn btn-outline-success" type="button">Car</button></a></li>
							<li><a href="./adminUser.jsp"><button class="btn btn-outline-success" type="button">Users</button></a></li>
							<li id="right"><a href="./returnCar.jsp"><button class="btn btn-outline-success" type="button">Return a rental</button></a></li>
					</ul>
					<div class="confirmation">
						<p>Logged in as :<%=user.getFirst_name() %> <%=user.getLast_Name() %></p>
					</div>
				</div>
			</nav>
<h3>Please send the error seen below to an IT Specialist</h3>
<% 
ErrorBean errorbean = (ErrorBean)request.getAttribute("errorbean");
out.println(errorbean.getError());
%>

</body>
</html>